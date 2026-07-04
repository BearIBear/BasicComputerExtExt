package ru.ifmo.cs.bcomp.assembler;

import org.junit.Test;
import static org.junit.Assert.*;

public class AsmNgTest {

    @Test
    public void testAsmNgOutOfBounds() {
        AsmNg asm = new AsmNg("LD #256");
        asm.compile();
        assertFalse(asm.getErrors().isEmpty());
        assertTrue(asm.getErrors().get(0).contains("Second pass: number exceed limits [-128..255]"));

        AsmNg asm2 = new AsmNg("LD #-129");
        asm2.compile();
        assertFalse(asm2.getErrors().isEmpty());
        assertTrue(asm2.getErrors().get(0).contains("Second pass: number exceed limits [-128..255]"));

        AsmNg asm3 = new AsmNg("LD #128");
        asm3.compile();
        assertTrue(asm3.getErrors().isEmpty());
        assertFalse(asm3.getWarnings().isEmpty());
        assertTrue(asm3.getWarnings().get(0).contains("Second pass: number 128 exceeds signed 8-bit limits [-128..127] and will be sign-extended to 16-bit"));

        AsmNg asm4 = new AsmNg("LD #0xFF");
        asm4.compile();
        assertTrue(asm4.getErrors().isEmpty());
        assertFalse(asm4.getWarnings().isEmpty());
        assertTrue(asm4.getWarnings().get(0).contains("Second pass: number 255 exceeds signed 8-bit limits [-128..127] and will be sign-extended to 16-bit"));
    }

    @Test
    public void testAsmNgDisplacementOutOfBounds() {
        AsmNg asm = new AsmNg(
                "ORG 000h\n" +
                "start: NOP\n" +
                "ORG 100h\n" +
                "BR start\n"
        );
        asm.compile();
        assertFalse(asm.getErrors().isEmpty());
        assertTrue(asm.getErrors().get(0).contains("Second pass: label start displacement exceed limits [-128..127]"));
    }

    @Test
    public void testAsmNgMultipleOrg() {
        AsmNg asm = new AsmNg(
                "ORG 0x30\n" +
                "CLA\n" +
                "ORG 0x20\n" +
                "CLA\n" +
                "ORG 0x10\n"
        );
        Program prog = asm.compile();
        assertTrue(asm.getErrors().isEmpty());
        assertEquals(0x10, prog.start_address);

        AsmNg asm2 = new AsmNg(
                "ORG 0x30\n" +
                "CLA\n" +
                "ORG 0x50\n" +
                "CLA\n" +
                "ORG 0x40\n"
        );
        Program prog2 = asm2.compile();
        assertTrue(asm2.getErrors().isEmpty());
        assertEquals(0x40, prog2.start_address);
    }

    @Test
    public void testAsmNgOrgOutOfBounds() {
        AsmNg asm = new AsmNg("ORG 0x800\nCLA");
        asm.compile();
        assertFalse(asm.getErrors().isEmpty());
        assertTrue(asm.getErrors().get(0).contains("First pass: ORG address 0x800 out of range [0..0x7FF]"));

        AsmNg asm2 = new AsmNg("ORG -0x1\nCLA");
        asm2.compile();
        assertFalse(asm2.getErrors().isEmpty());
        assertTrue(asm2.getErrors().get(0).contains("First pass: ORG address 0xffffffff out of range [0..0x7FF]"));
    }
}
