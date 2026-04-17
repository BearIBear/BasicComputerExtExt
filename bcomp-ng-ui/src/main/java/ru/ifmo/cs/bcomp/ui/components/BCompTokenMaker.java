/*
 * Custom TokenMaker for the BasicComputer (BComp) assembler language.
 * Provides syntax highlighting for BEVM instructions, directives,
 * registers, labels, comments (;), hex/decimal numbers, and strings.
 */

package ru.ifmo.cs.bcomp.ui.components;

import javax.swing.text.Segment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fife.ui.rsyntaxtextarea.AbstractTokenMaker;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMap;

/**
 * A {@link org.fife.ui.rsyntaxtextarea.TokenMaker} for the BasicComputer
 * (BEVM) assembler language used at ITMO University.
 *
 * @author Generated for BasicComputerExt project
 */
public class BCompTokenMaker extends AbstractTokenMaker {

    /**
     * Style identifier for registering this TokenMaker with a
     * {@link org.fife.ui.rsyntaxtextarea.TokenMakerFactory}.
     */
    public static final String SYNTAX_STYLE_BCOMP_ASM = "text/bcomp-asm";

    private int currentTokenStart;
    private int currentTokenType;

    /** Tracks whether the current number token contains hex indicators. */
    private boolean hexNumber;

    /** True when we are past an instruction keyword on the current line,
     *  so bare identifiers in operand position are treated as label refs. */
    private boolean afterInstruction;

    /** Pattern to find label definitions (e.g. "HELLO:" at start or after whitespace). */
    private static final Pattern LABEL_PATTERN =
            Pattern.compile("(?m)^\\s*([A-Za-z_\u0400-\u04FF][A-Za-z0-9_.\u0400-\u04FF]*)\\s*:");

    /** Set of currently known label names (case-sensitive, updated from document). */
    private static volatile Set<String> knownLabels = Collections.emptySet();

    /**
     * Scans the given document text for label definitions and updates the
     * known labels set. Called from {@code AssemblerView} on text changes.
     */
    public static void updateKnownLabels(String documentText) {
        Set<String> labels = new HashSet<String>();
        Matcher m = LABEL_PATTERN.matcher(documentText);
        while (m.find()) {
            labels.add(m.group(1));
        }
        knownLabels = labels;
    }

    /* ------------------------------------------------------------------ */

    @Override
    public TokenMap getWordsToHighlight() {
        TokenMap tokenMap = new TokenMap(true); // case-insensitive

        // --- Address instructions (RESERVED_WORD) ---
        tokenMap.put("AND",  Token.RESERVED_WORD);
        tokenMap.put("OR",   Token.RESERVED_WORD);
        tokenMap.put("ADD",  Token.RESERVED_WORD);
        tokenMap.put("ADC",  Token.RESERVED_WORD);
        tokenMap.put("SUB",  Token.RESERVED_WORD);
        tokenMap.put("CMP",  Token.RESERVED_WORD);
        tokenMap.put("LOOP", Token.RESERVED_WORD);
        tokenMap.put("LD",   Token.RESERVED_WORD);
        tokenMap.put("SWAM", Token.RESERVED_WORD);
        tokenMap.put("JUMP", Token.RESERVED_WORD);
        tokenMap.put("CALL", Token.RESERVED_WORD);
        tokenMap.put("ST",   Token.RESERVED_WORD);

        // --- Addressless instructions (RESERVED_WORD) ---
        tokenMap.put("NOP",   Token.RESERVED_WORD);
        tokenMap.put("HLT",   Token.RESERVED_WORD);
        tokenMap.put("CLA",   Token.RESERVED_WORD);
        tokenMap.put("NOT",   Token.RESERVED_WORD);
        tokenMap.put("CMA",   Token.RESERVED_WORD);
        tokenMap.put("COM",   Token.RESERVED_WORD);
        tokenMap.put("CLC",   Token.RESERVED_WORD);
        tokenMap.put("CMC",   Token.RESERVED_WORD);
        tokenMap.put("ROL",   Token.RESERVED_WORD);
        tokenMap.put("ROR",   Token.RESERVED_WORD);
        tokenMap.put("ASL",   Token.RESERVED_WORD);
        tokenMap.put("ASR",   Token.RESERVED_WORD);
        tokenMap.put("SXTB",  Token.RESERVED_WORD);
        tokenMap.put("SWAB",  Token.RESERVED_WORD);
        tokenMap.put("INC",   Token.RESERVED_WORD);
        tokenMap.put("DEC",   Token.RESERVED_WORD);
        tokenMap.put("NEG",   Token.RESERVED_WORD);
        tokenMap.put("POP",   Token.RESERVED_WORD);
        tokenMap.put("POPF",  Token.RESERVED_WORD);
        tokenMap.put("RET",   Token.RESERVED_WORD);
        tokenMap.put("IRET",  Token.RESERVED_WORD);
        tokenMap.put("PUSH",  Token.RESERVED_WORD);
        tokenMap.put("PUSHF", Token.RESERVED_WORD);
        tokenMap.put("SWAP",  Token.RESERVED_WORD);

        // --- Branch instructions (RESERVED_WORD_2) ---
        tokenMap.put("BEQ",  Token.RESERVED_WORD_2);
        tokenMap.put("BNE",  Token.RESERVED_WORD_2);
        tokenMap.put("BMI",  Token.RESERVED_WORD_2);
        tokenMap.put("BPL",  Token.RESERVED_WORD_2);
        tokenMap.put("BCS",  Token.RESERVED_WORD_2);
        tokenMap.put("BCC",  Token.RESERVED_WORD_2);
        tokenMap.put("BVS",  Token.RESERVED_WORD_2);
        tokenMap.put("BVC",  Token.RESERVED_WORD_2);
        tokenMap.put("BLT",  Token.RESERVED_WORD_2);
        tokenMap.put("BGE",  Token.RESERVED_WORD_2);
        tokenMap.put("BR",   Token.RESERVED_WORD_2);
        // branch aliases
        tokenMap.put("BZS",  Token.RESERVED_WORD_2);
        tokenMap.put("BZC",  Token.RESERVED_WORD_2);
        tokenMap.put("BNS",  Token.RESERVED_WORD_2);
        tokenMap.put("BNC",  Token.RESERVED_WORD_2);
        tokenMap.put("BLO",  Token.RESERVED_WORD_2);
        tokenMap.put("BHIS", Token.RESERVED_WORD_2);

        // --- I/O instructions (FUNCTION) ---
        tokenMap.put("IN",   Token.FUNCTION);
        tokenMap.put("OUT",  Token.FUNCTION);
        tokenMap.put("INT",  Token.FUNCTION);
        tokenMap.put("DI",   Token.FUNCTION);
        tokenMap.put("EI",   Token.FUNCTION);

        // --- Directives (PREPROCESSOR) ---
        tokenMap.put("ORG",  Token.PREPROCESSOR);
        tokenMap.put("WORD", Token.PREPROCESSOR);
        tokenMap.put("END",  Token.PREPROCESSOR);
        tokenMap.put("DUP",  Token.PREPROCESSOR);
        tokenMap.put("DUPLICATE", Token.PREPROCESSOR);

        // --- Registers (DATA_TYPE) ---
        tokenMap.put("SP",   Token.DATA_TYPE);
        tokenMap.put("IP",   Token.DATA_TYPE);

        // --- Russian mnemonics for address instructions ---
        tokenMap.put("\u0418",                             Token.RESERVED_WORD); // И  (AND)
        tokenMap.put("\u0418\u041b\u0418",                 Token.RESERVED_WORD); // ИЛИ (OR)
        tokenMap.put("\u041f\u041b\u042e\u0421",           Token.RESERVED_WORD); // ПЛЮС (ADD)
        tokenMap.put("\u041f\u041b\u042e\u0421\u0421",     Token.RESERVED_WORD); // ПЛЮСС (ADC)
        tokenMap.put("\u041c\u0418\u041d\u0423\u0421",     Token.RESERVED_WORD); // МИНУС (SUB)
        tokenMap.put("\u0421\u0420\u0410\u0412",           Token.RESERVED_WORD); // СРАВ (CMP)
        tokenMap.put("\u041a\u0420\u0423\u0413",           Token.RESERVED_WORD); // КРУГ (LOOP)
        tokenMap.put("\u041d\u042f\u041c",                 Token.RESERVED_WORD); // НЯМ (LD)
        tokenMap.put("\u041e\u0411\u041c\u0415\u041d",     Token.RESERVED_WORD); // ОБМЕН (SWAM)
        tokenMap.put("\u041f\u0420\u042b\u0413",           Token.RESERVED_WORD); // ПРЫГ (JUMP)
        tokenMap.put("\u0412\u0416\u0423\u0425",           Token.RESERVED_WORD); // ВЖУХ (CALL)
        tokenMap.put("\u0422\u042c\u0424\u0423",           Token.RESERVED_WORD); // ТЬФУ (ST)

        // --- Russian mnemonics for addressless instructions ---
        tokenMap.put("\u041f\u0420\u041e\u041f",           Token.RESERVED_WORD); // ПРОП (NOP)
        tokenMap.put("\u0421\u0422\u041e\u041f",           Token.RESERVED_WORD); // СТОП (HLT)
        tokenMap.put("\u0427\u0418\u0421\u0422\u042c",     Token.RESERVED_WORD); // ЧИСТЬ (CLA)
        tokenMap.put("\u041d\u0415",                       Token.RESERVED_WORD); // НЕ (NOT alias)
        tokenMap.put("\u0421\u0411\u0410",                 Token.RESERVED_WORD); // СБА (NOT alias)
        tokenMap.put("\u041d\u0415\u0422\u042c",           Token.RESERVED_WORD); // НЕТЬ (NOT)
        tokenMap.put("\u0427\u0418\u0421\u0422\u0426",     Token.RESERVED_WORD); // ЧИСТЦ (CLC)
        tokenMap.put("\u0418\u041d\u0412\u0426",           Token.RESERVED_WORD); // ИНВЦ (CMC)
        tokenMap.put("\u0426\u041b\u0415\u0412",           Token.RESERVED_WORD); // ЦЛЕВ (ROL)
        tokenMap.put("\u0426\u041f\u0420\u0410\u0412",     Token.RESERVED_WORD); // ЦПРАВ (ROR)
        tokenMap.put("\u0410\u041b\u0415\u0412",           Token.RESERVED_WORD); // АЛЕВ (ASL)
        tokenMap.put("\u0410\u041f\u0420\u0410\u0412",     Token.RESERVED_WORD); // АПРАВ (ASR)
        tokenMap.put("\u0420\u0410\u0421\u0428",           Token.RESERVED_WORD); // РАСШ (SXTB)
        tokenMap.put("\u041d\u0410\u041e\u0411\u041e\u0420\u041e\u0422", Token.RESERVED_WORD); // НАОБОРОТ (SWAB)
        tokenMap.put("\u0423\u0412\u0415\u041b",           Token.RESERVED_WORD); // УВЕЛ (INC)
        tokenMap.put("\u0423\u041c\u0415\u041d",           Token.RESERVED_WORD); // УМЕН (DEC)
        tokenMap.put("\u041e\u0422\u0420\u0418\u0426",     Token.RESERVED_WORD); // ОТРИЦ (NEG)
        tokenMap.put("\u0412\u042b\u041d\u042c",           Token.RESERVED_WORD); // ВЫНЬ (POP)
        tokenMap.put("\u0412\u042b\u041d\u042c\u0424",     Token.RESERVED_WORD); // ВЫНЬФ (POPF)
        tokenMap.put("\u0412\u041e\u0417\u0412\u0420",     Token.RESERVED_WORD); // ВОЗВР (RET)
        tokenMap.put("\u0412\u041e\u0417\u0412\u0420\u041f", Token.RESERVED_WORD); // ВОЗВРП (IRET)
        tokenMap.put("\u0421\u0423\u041d\u042c",           Token.RESERVED_WORD); // СУНЬ (PUSH)
        tokenMap.put("\u0421\u0423\u041d\u042c\u0424",     Token.RESERVED_WORD); // СУНЬФ (PUSHF)
        tokenMap.put("\u041c\u0415\u041d\u042c",           Token.RESERVED_WORD); // МЕНЬ (SWAP)

        // --- Russian mnemonics for branch ---
        tokenMap.put("\u0411\u042f\u041a\u0410",           Token.RESERVED_WORD_2); // БЯКА (BEQ)

        // --- Russian mnemonics for I/O ---
        tokenMap.put("\u041d\u0418\u0417\u042f",           Token.FUNCTION); // НИЗЯ (DI)
        tokenMap.put("\u041b\u042c\u0417\u042f",           Token.FUNCTION); // ЛЬЗЯ (EI)
        tokenMap.put("\u0412\u0412\u041e\u0414",           Token.FUNCTION); // ВВОД (IN)
        tokenMap.put("\u0412\u042b\u0412\u041e\u0414",     Token.FUNCTION); // ВЫВОД (OUT)
        tokenMap.put("\u041f\u0420\u0415\u0420",           Token.FUNCTION); // ПРЕР (INT)

        return tokenMap;
    }

    /**
     * Overrides the default addToken to reclassify identifiers:
     * - known keywords → their registered token type
     * - after an instruction keyword, known labels → VARIABLE (label reference)
     */
    @Override
    public void addToken(Segment segment, int start, int end,
                         int tokenType, int startOffset) {
        if (tokenType == Token.IDENTIFIER) {
            int value = wordsToHighlight.get(segment, start, end);
            if (value != -1) {
                tokenType = value;
            } else if (afterInstruction) {
                // Check if this identifier is a known label
                String id = new String(segment.array, start, end - start + 1);
                if (knownLabels.contains(id)) {
                    tokenType = Token.VARIABLE;
                }
            }
        }
        // Track instruction context: after an instruction or branch keyword,
        // subsequent identifiers on the same line are label references.
        if (tokenType == Token.RESERVED_WORD || tokenType == Token.RESERVED_WORD_2) {
            afterInstruction = true;
        }
        super.addToken(segment, start, end, tokenType, startOffset);
    }

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return new String[] { ";", null };
    }

    @Override
    public boolean getCurlyBracesDenoteCodeBlocks(int languageIndex) {
        return false;
    }

    private boolean isIdentifierChar(char c) {
        return RSyntaxUtilities.isLetterOrDigit(c) || c == '_' || c == '.'
                || (c >= '\u0400' && c <= '\u04FF');
    }

    private boolean isIdentifierStartChar(char c) {
        return RSyntaxUtilities.isLetter(c) || c == '_'
                || (c >= '\u0400' && c <= '\u04FF');
    }

    /** Returns the correct number token type based on the hexNumber flag. */
    private int getNumberTokenType() {
        return hexNumber
                ? Token.LITERAL_NUMBER_HEXADECIMAL
                : Token.LITERAL_NUMBER_DECIMAL_INT;
    }

    /**
     * Returns a linked list of tokens representing the given text.
     */
    @Override
    public Token getTokenList(Segment text, int startTokenType,
                              int startOffset) {

        resetTokenList();

        char[] array = text.array;
        int offset = text.offset;
        int count = text.count;
        int end = offset + count;

        int newStartOffset = startOffset - offset;

        currentTokenStart = offset;
        currentTokenType = startTokenType;
        hexNumber = false;
        afterInstruction = false;

        for (int i = offset; i < end; i++) {

            char c = array[i];

            switch (currentTokenType) {

                case Token.NULL:
                    currentTokenStart = i;
                    hexNumber = false;

                    if (c == ';') {
                        currentTokenType = Token.COMMENT_EOL;
                    } else if (c == '"') {
                        currentTokenType = Token.LITERAL_STRING_DOUBLE_QUOTE;
                    } else if (c == ' ' || c == '\t') {
                        currentTokenType = Token.WHITESPACE;
                    } else if (RSyntaxUtilities.isDigit(c)) {
                        currentTokenType = Token.LITERAL_NUMBER_DECIMAL_INT;
                    } else if (c == '-') {
                        // Peek ahead: if next char is digit or 0x, treat as number
                        if (i + 1 < end && (RSyntaxUtilities.isDigit(array[i + 1]))) {
                            currentTokenType = Token.LITERAL_NUMBER_DECIMAL_INT;
                        } else {
                            currentTokenType = Token.OPERATOR;
                        }
                    } else if (c == '$') {
                        // '$' prefix for label references — collect $ + identifier as VARIABLE
                        currentTokenType = Token.VARIABLE;
                    } else if (isIdentifierStartChar(c)) {
                        currentTokenType = Token.IDENTIFIER;
                    } else {
                        currentTokenType = Token.OPERATOR;
                    }
                    break;

                case Token.WHITESPACE:
                    if (c != ' ' && c != '\t') {
                        addToken(text, currentTokenStart, i - 1,
                                Token.WHITESPACE,
                                newStartOffset + currentTokenStart);
                        currentTokenStart = i;
                        currentTokenType = Token.NULL;
                        i--;
                    }
                    break;

                case Token.IDENTIFIER:
                    if (!isIdentifierChar(c)) {
                        if (c == ':') {
                            // Label definition — include the colon, emit directly as VARIABLE
                            // (bypass addToken override so keyword check doesn't interfere)
                            super.addToken(text, currentTokenStart, i,
                                    Token.VARIABLE,
                                    newStartOffset + currentTokenStart);
                            currentTokenType = Token.NULL;
                            currentTokenStart = i + 1;
                        } else {
                            addToken(text, currentTokenStart, i - 1,
                                    Token.IDENTIFIER,
                                    newStartOffset + currentTokenStart);
                            currentTokenStart = i;
                            currentTokenType = Token.NULL;
                            i--;
                        }
                    }
                    break;

                case Token.VARIABLE:
                    // Collecting $label reference or bare label reference
                    // Allow identifier chars after the initial $ or identifier start
                    if (!isIdentifierChar(c)) {
                        // Emit the entire $label as VARIABLE (skip keyword check)
                        super.addToken(text, currentTokenStart, i - 1,
                                Token.VARIABLE,
                                newStartOffset + currentTokenStart);
                        currentTokenStart = i;
                        currentTokenType = Token.NULL;
                        i--;
                    }
                    break;

                case Token.LITERAL_NUMBER_DECIMAL_INT:
                    if (RSyntaxUtilities.isDigit(c)) {
                        // still a number
                    } else if ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')
                            || c == 'x' || c == 'X') {
                        // hex indicator
                        hexNumber = true;
                    } else if (c == 'h' || c == 'H') {
                        // trailing H suffix = hex, include it and emit
                        hexNumber = true;
                        addToken(text, currentTokenStart, i,
                                Token.LITERAL_NUMBER_HEXADECIMAL,
                                newStartOffset + currentTokenStart);
                        currentTokenType = Token.NULL;
                        currentTokenStart = i + 1;
                    } else if (c == 'd' || c == 'D') {
                        // 0d prefix = decimal, keep going
                    } else {
                        addToken(text, currentTokenStart, i - 1,
                                getNumberTokenType(),
                                newStartOffset + currentTokenStart);
                        currentTokenStart = i;
                        currentTokenType = Token.NULL;
                        hexNumber = false;
                        i--;
                    }
                    break;

                case Token.LITERAL_STRING_DOUBLE_QUOTE:
                    if (c == '"') {
                        addToken(text, currentTokenStart, i,
                                Token.LITERAL_STRING_DOUBLE_QUOTE,
                                newStartOffset + currentTokenStart);
                        currentTokenType = Token.NULL;
                        currentTokenStart = i + 1;
                    }
                    break;

                case Token.COMMENT_EOL:
                    // consume everything to end of line
                    break;

                case Token.OPERATOR:
                    // Single-char operator tokens
                    addToken(text, currentTokenStart, i - 1,
                            Token.OPERATOR,
                            newStartOffset + currentTokenStart);
                    currentTokenStart = i;
                    currentTokenType = Token.NULL;
                    i--;
                    break;

                default:
                    if (c == ' ' || c == '\t') {
                        addToken(text, currentTokenStart, i - 1,
                                Token.IDENTIFIER,
                                newStartOffset + currentTokenStart);
                        currentTokenStart = i;
                        currentTokenType = Token.WHITESPACE;
                    }
                    break;
            }
        }

        // Flush remaining token at end of line
        switch (currentTokenType) {
            case Token.NULL:
                addNullToken();
                break;
            case Token.LITERAL_NUMBER_DECIMAL_INT:
                addToken(text, currentTokenStart, end - 1,
                        getNumberTokenType(),
                        newStartOffset + currentTokenStart);
                addNullToken();
                break;
            default:
                addToken(text, currentTokenStart, end - 1,
                        currentTokenType,
                        newStartOffset + currentTokenStart);
                addNullToken();
                break;
        }

        return firstToken;
    }
}
