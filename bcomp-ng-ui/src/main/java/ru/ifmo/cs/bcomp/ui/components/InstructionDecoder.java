/*
 * Decodes a 16-bit CR (Command Register) value into a human-readable
 * assembly instruction string such as "LD (ITER_POINTER)+" or "CMP #1".
 *
 * When a compiled Program is available the decoder uses the stored
 * InstructionWord objects (which carry the original label references and
 * addressing modes) to produce the richest possible output.
 *
 * When no program is available it falls back to pure bit-level decoding
 * of the opcode / addressing-mode / operand fields.
 */
package ru.ifmo.cs.bcomp.ui.components;

import ru.ifmo.cs.bcomp.assembler.Instruction;
import ru.ifmo.cs.bcomp.assembler.InstructionWord;
import ru.ifmo.cs.bcomp.assembler.MemoryWord;
import ru.ifmo.cs.bcomp.assembler.Program;

import java.util.HashMap;
import java.util.Map;

public class InstructionDecoder {

    /* ----- cached program data ------------------------------------------ */

    /** value → source-level text  (built from compiled Program) */
    private volatile Map<Integer, String> valueToText = null;

    /** Called after a successful compilation to cache instruction texts. */
    public void setProgram(Program program) {
        if (program == null || program.content == null) {
            valueToText = null;
            return;
        }

        Map<Integer, String> map = new HashMap<>();
        for (Map.Entry<Integer, MemoryWord> entry : program.content.entrySet()) {
            MemoryWord mw = entry.getValue();
            if (mw instanceof InstructionWord) {
                InstructionWord iw = (InstructionWord) mw;
                if (iw.instruction != null && iw.value != MemoryWord.UNDEFINED) {
                    String text = buildTextFromInstructionWord(iw);
                    // Only store the first encountered mapping for a value
                    // (different addresses may compile to the same opcode)
                    if (!map.containsKey(iw.value)) {
                        map.put(iw.value, text);
                    }
                }
            }
        }
        valueToText = map;
    }

    public void clearProgram() {
        valueToText = null;
    }

    /* ----- public API --------------------------------------------------- */

    /**
     * Returns a human-readable representation of the instruction encoded in
     * {@code crValue}.  If a compiled program is available and contains this
     * exact value the full source-level text is returned; otherwise a
     * generic decode is produced.
     */
    public String decode(int crValue) {
        crValue &= 0xFFFF;

        // 1. Try the cached program mapping first
        Map<Integer, String> cached = valueToText;
        if (cached != null) {
            String text = cached.get(crValue);
            if (text != null) return text;
        }

        // 2. Fall back to generic bit-level decoding
        return genericDecode(crValue);
    }

    /* ----- helpers ------------------------------------------------------- */

    /** Build text from a compiled InstructionWord (has labels, addressing, etc.) */
    private static String buildTextFromInstructionWord(InstructionWord iw) {
        StringBuilder sb = new StringBuilder();
        sb.append(iw.instruction.mnemonic);

        switch (iw.instruction.type) {
            case ADDR:
                if (iw.operand != null) {
                    sb.append(' ').append(iw.operand.toString());
                }
                break;
            case BRANCH:
                if (iw.operand != null && iw.operand.reference != null) {
                    sb.append(' ').append(iw.operand.reference);
                }
                break;
            case IO:
                if (iw.device != MemoryWord.UNDEFINED) {
                    sb.append(' ').append(iw.device);
                }
                break;
            case NONADDR:
                // nothing to add
                break;
        }
        return sb.toString();
    }

    /**
     * Pure bit-level decoding when no compiled program is available.
     * Works for all instruction types (address / addressless / branch / IO).
     */
    private static String genericDecode(int v) {
        // --- try to match an addressless instruction first (bits 15..0) ---
        // Addressless instructions have 0 in the top bit and various patterns.
        // The opcode is in bits 15-8 for most, but some share upper bits.
        // We iterate all known instructions and try to match.

        // Check if highest nibble (bits 15-12) is 0x0 or 0x1 -> could be NONADDR/IO
        int top4 = (v >> 12) & 0xF;

        if (top4 == 0x0 || top4 == 0x1) {
            // Try exact match for addressless/IO instructions
            for (Instruction instr : Instruction.values) {
                if (instr == Instruction.END) continue;
                if (instr.type == Instruction.Type.NONADDR) {
                    if (v == instr.opcode) {
                        return instr.mnemonic;
                    }
                }
                if (instr.type == Instruction.Type.IO) {
                    int mask = (instr == Instruction.INT) ? 0xFFF8 : 0xFF00;
                    if ((v & mask) == instr.opcode) {
                        int operand = v & ~mask;
                        return instr.mnemonic + " " + operand;
                    }
                }
            }
            // If no exact match, might be NOP-like (0x0000 area)
            return "??? 0x" + Integer.toHexString(v);
        }

        // --- branch instructions: top nibble 0xF ---
        if (top4 == 0xF) {
            int branchOpcode = v & 0xFF00;
            int offset = v & 0xFF;
            for (Instruction instr : Instruction.values) {
                if (instr == Instruction.END) continue;
                if (instr.type == Instruction.Type.BRANCH && instr.opcode == branchOpcode) {
                    if (offset == 0) {
                        return instr.mnemonic;
                    }
                    // Show signed offset
                    int signedOffset = (offset > 127) ? offset - 256 : offset;
                    return instr.mnemonic + " " + (signedOffset >= 0 ? "+" : "") + signedOffset;
                }
            }
            return "??? 0x" + Integer.toHexString(v);
        }

        // --- address instructions: top nibble 0x2..0xE ---
        // Opcode is bits 15-13 (3 bits shifted by 13) -> range 1..7 maps to
        // instructions AND..ST.  But wait, let's look at the actual opcodes:
        //   AND=0x2000, OR=0x3000, ADD=0x4000, ADC=0x5000,
        //   SUB=0x6000, CMP=0x7000, LOOP=0x8000, LD=0xA000,
        //   SWAM=0xB000, JUMP=0xC000, CALL=0xD000, ST=0xE000
        // So the opcode is bits 15-12 (top nibble) for most, but LOOP (0x8)
        // and an unnamed gap (0x9) exist.  Let's match by masking top nibble.

        // Special case: BR = 0xCE00 — this overlaps with JUMP+addressing mode
        // BR is actually encoded as JUMP with addressing mode bits = 0x0E00
        // which is DIRECT_RELATIVE. We need to match JUMP first, then let
        // addressing-mode decoding handle it.
        // Actually no — BR (0xCE00) is type BRANCH in the enum, not ADDR.
        // Let's check if it matches BR first.
        if ((v & 0xFF00) == Instruction.BR.opcode) {
            int offset = v & 0xFF;
            if (offset == 0) {
                return "BR";
            }
            int signedOffset = (offset > 127) ? offset - 256 : offset;
            return "BR " + (signedOffset >= 0 ? "+" : "") + signedOffset;
        }

        int instrOpcode = v & 0xF000;
        Instruction matched = null;
        for (Instruction instr : Instruction.values) {
            if (instr == Instruction.END) continue;
            if (instr.type == Instruction.Type.ADDR && instr.opcode == instrOpcode) {
                matched = instr;
                break;
            }
        }

        if (matched == null) {
            return "??? 0x" + Integer.toHexString(v);
        }

        // Decode addressing mode from bits 11-8
        int addrMode = (v >> 8) & 0xF;
        int operand = v & 0xFF;
        // For DIRECT_ABSOLUTE the operand is 11 bits (bits 10-0), mode bits are in 12-11
        // Actually, let's re-examine the encoding:
        //   DIRECT_ABSOLUTE: opcode | (addr & 0x7FF)           -> bits 10-0 are address
        //   INDIRECT:        opcode | 0x0800 | displacement    -> bit 11=1, bits 10-8 = 00, bits 7-0 = disp
        //   POST_INCREMENT:  opcode | 0x0A00 | displacement
        //   PRE_DECREMENT:   opcode | 0x0B00 | displacement
        //   DISPLACEMENT_SP: opcode | 0x0C00 | displacement
        //   DIRECT_RELATIVE: opcode | 0x0E00 | displacement
        //   DIRECT_LOAD:     opcode | 0x0F00 | value

        int bits11_8 = (v >> 8) & 0xF;
        int low11 = v & 0x7FF;
        int low8 = v & 0xFF;

        StringBuilder sb = new StringBuilder();
        sb.append(matched.mnemonic).append(' ');

        if (bits11_8 < 0x8) {
            // DIRECT_ABSOLUTE: address is bits 10..0
            sb.append("$0x").append(Integer.toHexString(low11));
        } else {
            // Bits 11-8 determine addressing mode
            switch (bits11_8) {
                case 0x8: case 0x9:
                    // INDIRECT (0x08xx)
                    sb.append("(").append(formatSignedOffset(low8)).append(")");
                    break;
                case 0xA:
                    // POST_INCREMENT
                    sb.append("(").append(formatSignedOffset(low8)).append(")+");
                    break;
                case 0xB:
                    // PRE_DECREMENT
                    sb.append("-(").append(formatSignedOffset(low8)).append(")");
                    break;
                case 0xC:
                    // DISPLACEMENT_SP
                    sb.append("&").append(formatSignedOffset(low8));
                    break;
                case 0xD:
                    // Not standard — show raw
                    sb.append("?0x").append(Integer.toHexString(low8));
                    break;
                case 0xE:
                    // DIRECT_RELATIVE
                    sb.append(formatSignedOffset(low8));
                    break;
                case 0xF:
                    // DIRECT_LOAD
                    sb.append("#").append(formatUnsigned(low8));
                    break;
                default:
                    sb.append("0x").append(Integer.toHexString(v & 0xFFF));
            }
        }

        return sb.toString();
    }

    private static String formatSignedOffset(int val) {
        int signed = (val > 127) ? val - 256 : val;
        if (signed >= 0) return "+" + signed;
        return String.valueOf(signed);
    }

    private static String formatUnsigned(int val) {
        if (val <= 9) return String.valueOf(val);
        return "0x" + Integer.toHexString(val);
    }
}
