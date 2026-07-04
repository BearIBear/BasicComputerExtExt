// Generated from d:\programming\bevm-2\BasicComputerExt\bcomp-assembler\src\main\java\ru\ifmo\cs\bcomp\grammar\BCompNG.g4 by ANTLR 4.8
package ru.ifmo.cs.bcomp.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BCompNGParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, ORG=11, WORD=12, END=13, DUP=14, AND=15, OR=16, ADD=17, ADC=18, 
		SUB=19, CMP=20, LOOP=21, LD=22, SWAM=23, JUMP=24, CALL=25, ST=26, NOP=27, 
		HLT=28, CLA=29, NOT=30, CLC=31, CMC=32, ROL=33, ROR=34, ASL=35, ASR=36, 
		SXTB=37, SWAB=38, INC=39, DEC=40, NEG=41, POP=42, POPF=43, RET=44, IRET=45, 
		PUSH=46, PUSHF=47, SWAP=48, BEQ=49, BNE=50, BMI=51, BPL=52, BCS=53, BCC=54, 
		BVS=55, BVC=56, BLT=57, BGE=58, BR=59, DI=60, EI=61, IN=62, OUT=63, INT=64, 
		SP=65, IP=66, NAME=67, DECIMAL=68, HEX=69, COMMENT=70, STRING=71, EOL=72, 
		WS=73;
	public static final int
		RULE_prog = 0, RULE_line = 1, RULE_instructionLine = 2, RULE_instruction = 3, 
		RULE_directive = 4, RULE_orgAddress = 5, RULE_wordDirective = 6, RULE_wordArguments = 7, 
		RULE_wordArgument = 8, RULE_dupArgument = 9, RULE_count = 10, RULE_lbl = 11, 
		RULE_label = 12, RULE_dev = 13, RULE_operand = 14, RULE_directAbsolute = 15, 
		RULE_indirect = 16, RULE_postIncrement = 17, RULE_preDecrement = 18, RULE_displacementSP = 19, 
		RULE_directRelative = 20, RULE_directLoad = 21, RULE_address = 22, RULE_string = 23, 
		RULE_name = 24, RULE_number = 25, RULE_comment = 26, RULE_addr = 27, RULE_nonaddr = 28, 
		RULE_branch = 29, RULE_io = 30, RULE_sp = 31, RULE_ip = 32, RULE_org = 33, 
		RULE_word = 34, RULE_dup = 35, RULE_end = 36;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "line", "instructionLine", "instruction", "directive", "orgAddress", 
			"wordDirective", "wordArguments", "wordArgument", "dupArgument", "count", 
			"lbl", "label", "dev", "operand", "directAbsolute", "indirect", "postIncrement", 
			"preDecrement", "displacementSP", "directRelative", "directLoad", "address", 
			"string", "name", "number", "comment", "addr", "nonaddr", "branch", "io", 
			"sp", "ip", "org", "word", "dup", "end"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'$'", "'?'", "'('", "')'", "':'", "'+'", "'-'", "'&'", 
			"'#'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "ORG", 
			"WORD", "END", "DUP", "AND", "OR", "ADD", "ADC", "SUB", "CMP", "LOOP", 
			"LD", "SWAM", "JUMP", "CALL", "ST", "NOP", "HLT", "CLA", "NOT", "CLC", 
			"CMC", "ROL", "ROR", "ASL", "ASR", "SXTB", "SWAB", "INC", "DEC", "NEG", 
			"POP", "POPF", "RET", "IRET", "PUSH", "PUSHF", "SWAP", "BEQ", "BNE", 
			"BMI", "BPL", "BCS", "BCC", "BVS", "BVC", "BLT", "BGE", "BR", "DI", "EI", 
			"IN", "OUT", "INT", "SP", "IP", "NAME", "DECIMAL", "HEX", "COMMENT", 
			"STRING", "EOL", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "BCompNG.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BCompNGParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(BCompNGParser.EOF, 0); }
		public List<TerminalNode> EOL() { return getTokens(BCompNGParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(BCompNGParser.EOL, i);
		}
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(75);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & ((1L << (ORG - 11)) | (1L << (WORD - 11)) | (1L << (END - 11)) | (1L << (AND - 11)) | (1L << (OR - 11)) | (1L << (ADD - 11)) | (1L << (ADC - 11)) | (1L << (SUB - 11)) | (1L << (CMP - 11)) | (1L << (LOOP - 11)) | (1L << (LD - 11)) | (1L << (SWAM - 11)) | (1L << (JUMP - 11)) | (1L << (CALL - 11)) | (1L << (ST - 11)) | (1L << (NOP - 11)) | (1L << (HLT - 11)) | (1L << (CLA - 11)) | (1L << (NOT - 11)) | (1L << (CLC - 11)) | (1L << (CMC - 11)) | (1L << (ROL - 11)) | (1L << (ROR - 11)) | (1L << (ASL - 11)) | (1L << (ASR - 11)) | (1L << (SXTB - 11)) | (1L << (SWAB - 11)) | (1L << (INC - 11)) | (1L << (DEC - 11)) | (1L << (NEG - 11)) | (1L << (POP - 11)) | (1L << (POPF - 11)) | (1L << (RET - 11)) | (1L << (IRET - 11)) | (1L << (PUSH - 11)) | (1L << (PUSHF - 11)) | (1L << (SWAP - 11)) | (1L << (BEQ - 11)) | (1L << (BNE - 11)) | (1L << (BMI - 11)) | (1L << (BPL - 11)) | (1L << (BCS - 11)) | (1L << (BCC - 11)) | (1L << (BVS - 11)) | (1L << (BVC - 11)) | (1L << (BLT - 11)) | (1L << (BGE - 11)) | (1L << (BR - 11)) | (1L << (DI - 11)) | (1L << (EI - 11)) | (1L << (IN - 11)) | (1L << (OUT - 11)) | (1L << (INT - 11)) | (1L << (NAME - 11)) | (1L << (COMMENT - 11)))) != 0)) {
						{
						setState(74);
						line();
						}
					}

					setState(77);
					match(EOL);
					}
					} 
				}
				setState(82);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & ((1L << (ORG - 11)) | (1L << (WORD - 11)) | (1L << (END - 11)) | (1L << (AND - 11)) | (1L << (OR - 11)) | (1L << (ADD - 11)) | (1L << (ADC - 11)) | (1L << (SUB - 11)) | (1L << (CMP - 11)) | (1L << (LOOP - 11)) | (1L << (LD - 11)) | (1L << (SWAM - 11)) | (1L << (JUMP - 11)) | (1L << (CALL - 11)) | (1L << (ST - 11)) | (1L << (NOP - 11)) | (1L << (HLT - 11)) | (1L << (CLA - 11)) | (1L << (NOT - 11)) | (1L << (CLC - 11)) | (1L << (CMC - 11)) | (1L << (ROL - 11)) | (1L << (ROR - 11)) | (1L << (ASL - 11)) | (1L << (ASR - 11)) | (1L << (SXTB - 11)) | (1L << (SWAB - 11)) | (1L << (INC - 11)) | (1L << (DEC - 11)) | (1L << (NEG - 11)) | (1L << (POP - 11)) | (1L << (POPF - 11)) | (1L << (RET - 11)) | (1L << (IRET - 11)) | (1L << (PUSH - 11)) | (1L << (PUSHF - 11)) | (1L << (SWAP - 11)) | (1L << (BEQ - 11)) | (1L << (BNE - 11)) | (1L << (BMI - 11)) | (1L << (BPL - 11)) | (1L << (BCS - 11)) | (1L << (BCC - 11)) | (1L << (BVS - 11)) | (1L << (BVC - 11)) | (1L << (BLT - 11)) | (1L << (BGE - 11)) | (1L << (BR - 11)) | (1L << (DI - 11)) | (1L << (EI - 11)) | (1L << (IN - 11)) | (1L << (OUT - 11)) | (1L << (INT - 11)) | (1L << (NAME - 11)) | (1L << (COMMENT - 11)))) != 0)) {
				{
				setState(83);
				line();
				}
			}

			setState(86);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LineContext extends ParserRuleContext {
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public InstructionLineContext instructionLine() {
			return getRuleContext(InstructionLineContext.class,0);
		}
		public DirectiveContext directive() {
			return getRuleContext(DirectiveContext.class,0);
		}
		public LblContext lbl() {
			return getRuleContext(LblContext.class,0);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_line);
		try {
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				comment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				instructionLine();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(90);
				directive();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(91);
				lbl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionLineContext extends ParserRuleContext {
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public LblContext lbl() {
			return getRuleContext(LblContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public InstructionLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructionLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterInstructionLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitInstructionLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitInstructionLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionLineContext instructionLine() throws RecognitionException {
		InstructionLineContext _localctx = new InstructionLineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instructionLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(94);
				lbl();
				}
			}

			setState(97);
			instruction();
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMENT) {
				{
				setState(98);
				comment();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public AddrContext addr() {
			return getRuleContext(AddrContext.class,0);
		}
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public NonaddrContext nonaddr() {
			return getRuleContext(NonaddrContext.class,0);
		}
		public BranchContext branch() {
			return getRuleContext(BranchContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public IoContext io() {
			return getRuleContext(IoContext.class,0);
		}
		public DevContext dev() {
			return getRuleContext(DevContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_instruction);
		try {
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AND:
			case OR:
			case ADD:
			case ADC:
			case SUB:
			case CMP:
			case LOOP:
			case LD:
			case SWAM:
			case JUMP:
			case CALL:
			case ST:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				addr();
				setState(102);
				operand();
				}
				break;
			case NOP:
			case HLT:
			case CLA:
			case NOT:
			case CLC:
			case CMC:
			case ROL:
			case ROR:
			case ASL:
			case ASR:
			case SXTB:
			case SWAB:
			case INC:
			case DEC:
			case NEG:
			case POP:
			case POPF:
			case RET:
			case IRET:
			case PUSH:
			case PUSHF:
			case SWAP:
			case DI:
			case EI:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				nonaddr();
				}
				break;
			case BEQ:
			case BNE:
			case BMI:
			case BPL:
			case BCS:
			case BCC:
			case BVS:
			case BVC:
			case BLT:
			case BGE:
			case BR:
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				branch();
				setState(106);
				label();
				}
				break;
			case IN:
			case OUT:
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(108);
				io();
				setState(109);
				dev();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectiveContext extends ParserRuleContext {
		public OrgContext org() {
			return getRuleContext(OrgContext.class,0);
		}
		public OrgAddressContext orgAddress() {
			return getRuleContext(OrgAddressContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public WordDirectiveContext wordDirective() {
			return getRuleContext(WordDirectiveContext.class,0);
		}
		public EndContext end() {
			return getRuleContext(EndContext.class,0);
		}
		public DirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectiveContext directive() throws RecognitionException {
		DirectiveContext _localctx = new DirectiveContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_directive);
		int _la;
		try {
			setState(123);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ORG:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				org();
				setState(114);
				orgAddress();
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMENT) {
					{
					setState(115);
					comment();
					}
				}

				}
				break;
			case WORD:
			case NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				wordDirective();
				}
				break;
			case END:
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
				end();
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMENT) {
					{
					setState(120);
					comment();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrgAddressContext extends ParserRuleContext {
		public AddressContext address() {
			return getRuleContext(AddressContext.class,0);
		}
		public OrgAddressContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orgAddress; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterOrgAddress(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitOrgAddress(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitOrgAddress(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrgAddressContext orgAddress() throws RecognitionException {
		OrgAddressContext _localctx = new OrgAddressContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_orgAddress);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			address();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordDirectiveContext extends ParserRuleContext {
		public WordContext word() {
			return getRuleContext(WordContext.class,0);
		}
		public WordArgumentsContext wordArguments() {
			return getRuleContext(WordArgumentsContext.class,0);
		}
		public LblContext lbl() {
			return getRuleContext(LblContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public WordDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wordDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterWordDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitWordDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitWordDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordDirectiveContext wordDirective() throws RecognitionException {
		WordDirectiveContext _localctx = new WordDirectiveContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_wordDirective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(127);
				lbl();
				}
			}

			setState(130);
			word();
			setState(131);
			wordArguments();
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMENT) {
				{
				setState(132);
				comment();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordArgumentsContext extends ParserRuleContext {
		public List<WordArgumentContext> wordArgument() {
			return getRuleContexts(WordArgumentContext.class);
		}
		public WordArgumentContext wordArgument(int i) {
			return getRuleContext(WordArgumentContext.class,i);
		}
		public WordArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wordArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterWordArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitWordArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitWordArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordArgumentsContext wordArguments() throws RecognitionException {
		WordArgumentsContext _localctx = new WordArgumentsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_wordArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			wordArgument();
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(136);
				match(T__0);
				setState(137);
				wordArgument();
				}
				}
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordArgumentContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public DupArgumentContext dupArgument() {
			return getRuleContext(DupArgumentContext.class,0);
		}
		public WordArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wordArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterWordArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitWordArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitWordArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordArgumentContext wordArgument() throws RecognitionException {
		WordArgumentContext _localctx = new WordArgumentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_wordArgument);
		try {
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				match(T__1);
				setState(145);
				label();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				dupArgument();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DupArgumentContext extends ParserRuleContext {
		public CountContext count() {
			return getRuleContext(CountContext.class,0);
		}
		public DupContext dup() {
			return getRuleContext(DupContext.class,0);
		}
		public WordArgumentContext wordArgument() {
			return getRuleContext(WordArgumentContext.class,0);
		}
		public DupArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dupArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterDupArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitDupArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitDupArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DupArgumentContext dupArgument() throws RecognitionException {
		DupArgumentContext _localctx = new DupArgumentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_dupArgument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			count();
			setState(151);
			dup();
			setState(152);
			match(T__3);
			setState(153);
			wordArgument();
			setState(154);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CountContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public CountContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterCount(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitCount(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitCount(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CountContext count() throws RecognitionException {
		CountContext _localctx = new CountContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LblContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public LblContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lbl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterLbl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitLbl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitLbl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LblContext lbl() throws RecognitionException {
		LblContext _localctx = new LblContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_lbl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			label();
			setState(159);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DevContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public DevContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dev; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterDev(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitDev(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitDev(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DevContext dev() throws RecognitionException {
		DevContext _localctx = new DevContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_dev);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperandContext extends ParserRuleContext {
		public DirectAbsoluteContext directAbsolute() {
			return getRuleContext(DirectAbsoluteContext.class,0);
		}
		public IndirectContext indirect() {
			return getRuleContext(IndirectContext.class,0);
		}
		public PostIncrementContext postIncrement() {
			return getRuleContext(PostIncrementContext.class,0);
		}
		public PreDecrementContext preDecrement() {
			return getRuleContext(PreDecrementContext.class,0);
		}
		public DisplacementSPContext displacementSP() {
			return getRuleContext(DisplacementSPContext.class,0);
		}
		public DirectRelativeContext directRelative() {
			return getRuleContext(DirectRelativeContext.class,0);
		}
		public DirectLoadContext directLoad() {
			return getRuleContext(DirectLoadContext.class,0);
		}
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitOperand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitOperand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_operand);
		try {
			setState(172);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				directAbsolute();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				indirect();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(167);
				postIncrement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(168);
				preDecrement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(169);
				displacementSP();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(170);
				directRelative();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(171);
				directLoad();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectAbsoluteContext extends ParserRuleContext {
		public AddressContext address() {
			return getRuleContext(AddressContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public DirectAbsoluteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directAbsolute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterDirectAbsolute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitDirectAbsolute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitDirectAbsolute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectAbsoluteContext directAbsolute() throws RecognitionException {
		DirectAbsoluteContext _localctx = new DirectAbsoluteContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_directAbsolute);
		try {
			setState(177);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
			case HEX:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				address();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(T__1);
				setState(176);
				label();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndirectContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public IndirectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indirect; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterIndirect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitIndirect(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitIndirect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndirectContext indirect() throws RecognitionException {
		IndirectContext _localctx = new IndirectContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_indirect);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(T__3);
			setState(180);
			label();
			setState(181);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PostIncrementContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public PostIncrementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postIncrement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterPostIncrement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitPostIncrement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitPostIncrement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostIncrementContext postIncrement() throws RecognitionException {
		PostIncrementContext _localctx = new PostIncrementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_postIncrement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(T__3);
			setState(184);
			label();
			setState(185);
			match(T__4);
			setState(186);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PreDecrementContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public PreDecrementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preDecrement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterPreDecrement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitPreDecrement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitPreDecrement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreDecrementContext preDecrement() throws RecognitionException {
		PreDecrementContext _localctx = new PreDecrementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_preDecrement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(T__7);
			setState(189);
			match(T__3);
			setState(190);
			label();
			setState(191);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DisplacementSPContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public DisplacementSPContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_displacementSP; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterDisplacementSP(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitDisplacementSP(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitDisplacementSP(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DisplacementSPContext displacementSP() throws RecognitionException {
		DisplacementSPContext _localctx = new DisplacementSPContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_displacementSP);
		try {
			setState(201);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				match(T__8);
				setState(194);
				number();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(195);
				match(T__3);
				setState(196);
				sp();
				setState(197);
				match(T__6);
				setState(198);
				number();
				setState(199);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectRelativeContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public DirectRelativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directRelative; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterDirectRelative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitDirectRelative(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitDirectRelative(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectRelativeContext directRelative() throws RecognitionException {
		DirectRelativeContext _localctx = new DirectRelativeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_directRelative);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			label();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectLoadContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public DirectLoadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directLoad; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterDirectLoad(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitDirectLoad(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitDirectLoad(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectLoadContext directLoad() throws RecognitionException {
		DirectLoadContext _localctx = new DirectLoadContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_directLoad);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(T__9);
			setState(206);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddressContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public AddressContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_address; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterAddress(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitAddress(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitAddress(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddressContext address() throws RecognitionException {
		AddressContext _localctx = new AddressContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_address);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(BCompNGParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BCompNGParser.NAME, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode DECIMAL() { return getToken(BCompNGParser.DECIMAL, 0); }
		public TerminalNode HEX() { return getToken(BCompNGParser.HEX, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			_la = _input.LA(1);
			if ( !(_la==DECIMAL || _la==HEX) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(BCompNGParser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(COMMENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddrContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(BCompNGParser.AND, 0); }
		public TerminalNode OR() { return getToken(BCompNGParser.OR, 0); }
		public TerminalNode ADD() { return getToken(BCompNGParser.ADD, 0); }
		public TerminalNode ADC() { return getToken(BCompNGParser.ADC, 0); }
		public TerminalNode SUB() { return getToken(BCompNGParser.SUB, 0); }
		public TerminalNode CMP() { return getToken(BCompNGParser.CMP, 0); }
		public TerminalNode LOOP() { return getToken(BCompNGParser.LOOP, 0); }
		public TerminalNode LD() { return getToken(BCompNGParser.LD, 0); }
		public TerminalNode SWAM() { return getToken(BCompNGParser.SWAM, 0); }
		public TerminalNode JUMP() { return getToken(BCompNGParser.JUMP, 0); }
		public TerminalNode CALL() { return getToken(BCompNGParser.CALL, 0); }
		public TerminalNode ST() { return getToken(BCompNGParser.ST, 0); }
		public AddrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterAddr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitAddr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitAddr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddrContext addr() throws RecognitionException {
		AddrContext _localctx = new AddrContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_addr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << ADD) | (1L << ADC) | (1L << SUB) | (1L << CMP) | (1L << LOOP) | (1L << LD) | (1L << SWAM) | (1L << JUMP) | (1L << CALL) | (1L << ST))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonaddrContext extends ParserRuleContext {
		public TerminalNode NOP() { return getToken(BCompNGParser.NOP, 0); }
		public TerminalNode HLT() { return getToken(BCompNGParser.HLT, 0); }
		public TerminalNode CLA() { return getToken(BCompNGParser.CLA, 0); }
		public TerminalNode NOT() { return getToken(BCompNGParser.NOT, 0); }
		public TerminalNode CLC() { return getToken(BCompNGParser.CLC, 0); }
		public TerminalNode CMC() { return getToken(BCompNGParser.CMC, 0); }
		public TerminalNode ROL() { return getToken(BCompNGParser.ROL, 0); }
		public TerminalNode ROR() { return getToken(BCompNGParser.ROR, 0); }
		public TerminalNode ASL() { return getToken(BCompNGParser.ASL, 0); }
		public TerminalNode ASR() { return getToken(BCompNGParser.ASR, 0); }
		public TerminalNode SXTB() { return getToken(BCompNGParser.SXTB, 0); }
		public TerminalNode SWAB() { return getToken(BCompNGParser.SWAB, 0); }
		public TerminalNode INC() { return getToken(BCompNGParser.INC, 0); }
		public TerminalNode DEC() { return getToken(BCompNGParser.DEC, 0); }
		public TerminalNode NEG() { return getToken(BCompNGParser.NEG, 0); }
		public TerminalNode POP() { return getToken(BCompNGParser.POP, 0); }
		public TerminalNode POPF() { return getToken(BCompNGParser.POPF, 0); }
		public TerminalNode RET() { return getToken(BCompNGParser.RET, 0); }
		public TerminalNode IRET() { return getToken(BCompNGParser.IRET, 0); }
		public TerminalNode PUSH() { return getToken(BCompNGParser.PUSH, 0); }
		public TerminalNode PUSHF() { return getToken(BCompNGParser.PUSHF, 0); }
		public TerminalNode SWAP() { return getToken(BCompNGParser.SWAP, 0); }
		public TerminalNode EI() { return getToken(BCompNGParser.EI, 0); }
		public TerminalNode DI() { return getToken(BCompNGParser.DI, 0); }
		public NonaddrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonaddr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterNonaddr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitNonaddr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitNonaddr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonaddrContext nonaddr() throws RecognitionException {
		NonaddrContext _localctx = new NonaddrContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_nonaddr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOP) | (1L << HLT) | (1L << CLA) | (1L << NOT) | (1L << CLC) | (1L << CMC) | (1L << ROL) | (1L << ROR) | (1L << ASL) | (1L << ASR) | (1L << SXTB) | (1L << SWAB) | (1L << INC) | (1L << DEC) | (1L << NEG) | (1L << POP) | (1L << POPF) | (1L << RET) | (1L << IRET) | (1L << PUSH) | (1L << PUSHF) | (1L << SWAP) | (1L << DI) | (1L << EI))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BranchContext extends ParserRuleContext {
		public TerminalNode BEQ() { return getToken(BCompNGParser.BEQ, 0); }
		public TerminalNode BNE() { return getToken(BCompNGParser.BNE, 0); }
		public TerminalNode BMI() { return getToken(BCompNGParser.BMI, 0); }
		public TerminalNode BPL() { return getToken(BCompNGParser.BPL, 0); }
		public TerminalNode BCS() { return getToken(BCompNGParser.BCS, 0); }
		public TerminalNode BCC() { return getToken(BCompNGParser.BCC, 0); }
		public TerminalNode BVS() { return getToken(BCompNGParser.BVS, 0); }
		public TerminalNode BVC() { return getToken(BCompNGParser.BVC, 0); }
		public TerminalNode BLT() { return getToken(BCompNGParser.BLT, 0); }
		public TerminalNode BGE() { return getToken(BCompNGParser.BGE, 0); }
		public TerminalNode BR() { return getToken(BCompNGParser.BR, 0); }
		public BranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitBranch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BranchContext branch() throws RecognitionException {
		BranchContext _localctx = new BranchContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_branch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BEQ) | (1L << BNE) | (1L << BMI) | (1L << BPL) | (1L << BCS) | (1L << BCC) | (1L << BVS) | (1L << BVC) | (1L << BLT) | (1L << BGE) | (1L << BR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IoContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(BCompNGParser.IN, 0); }
		public TerminalNode OUT() { return getToken(BCompNGParser.OUT, 0); }
		public TerminalNode INT() { return getToken(BCompNGParser.INT, 0); }
		public IoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_io; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterIo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitIo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitIo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IoContext io() throws RecognitionException {
		IoContext _localctx = new IoContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_io);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			_la = _input.LA(1);
			if ( !(((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & ((1L << (IN - 62)) | (1L << (OUT - 62)) | (1L << (INT - 62)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpContext extends ParserRuleContext {
		public TerminalNode SP() { return getToken(BCompNGParser.SP, 0); }
		public SpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterSp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitSp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitSp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpContext sp() throws RecognitionException {
		SpContext _localctx = new SpContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_sp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(SP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IpContext extends ParserRuleContext {
		public TerminalNode IP() { return getToken(BCompNGParser.IP, 0); }
		public IpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ip; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterIp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitIp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitIp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IpContext ip() throws RecognitionException {
		IpContext _localctx = new IpContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_ip);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(IP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrgContext extends ParserRuleContext {
		public TerminalNode ORG() { return getToken(BCompNGParser.ORG, 0); }
		public OrgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_org; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterOrg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitOrg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitOrg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrgContext org() throws RecognitionException {
		OrgContext _localctx = new OrgContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_org);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(ORG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WordContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(BCompNGParser.WORD, 0); }
		public WordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_word; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordContext word() throws RecognitionException {
		WordContext _localctx = new WordContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_word);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DupContext extends ParserRuleContext {
		public TerminalNode DUP() { return getToken(BCompNGParser.DUP, 0); }
		public DupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterDup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitDup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitDup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DupContext dup() throws RecognitionException {
		DupContext _localctx = new DupContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_dup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			match(DUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EndContext extends ParserRuleContext {
		public TerminalNode END() { return getToken(BCompNGParser.END, 0); }
		public EndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).enterEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BCompNGListener ) ((BCompNGListener)listener).exitEnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BCompNGVisitor ) return ((BCompNGVisitor<? extends T>)visitor).visitEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndContext end() throws RecognitionException {
		EndContext _localctx = new EndContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_end);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3K\u00f1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\5\2N\n\2\3\2\7\2Q\n\2\f\2\16\2"+
		"T\13\2\3\2\5\2W\n\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3_\n\3\3\4\5\4b\n\4\3\4"+
		"\3\4\5\4f\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5r\n\5\3\6\3\6"+
		"\3\6\5\6w\n\6\3\6\3\6\3\6\5\6|\n\6\5\6~\n\6\3\7\3\7\3\b\5\b\u0083\n\b"+
		"\3\b\3\b\3\b\5\b\u0088\n\b\3\t\3\t\3\t\7\t\u008d\n\t\f\t\16\t\u0090\13"+
		"\t\3\n\3\n\3\n\3\n\3\n\5\n\u0097\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\5\20\u00af\n\20\3\21\3\21\3\21\5\21\u00b4\n\21\3\22\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\5\25\u00cc\n\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37"+
		"\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3&\2\2\'\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJ\2\7\3\2FG\3\2\21"+
		"\34\4\2\35\62>?\3\2\63=\3\2@B\2\u00e8\2R\3\2\2\2\4^\3\2\2\2\6a\3\2\2\2"+
		"\bq\3\2\2\2\n}\3\2\2\2\f\177\3\2\2\2\16\u0082\3\2\2\2\20\u0089\3\2\2\2"+
		"\22\u0096\3\2\2\2\24\u0098\3\2\2\2\26\u009e\3\2\2\2\30\u00a0\3\2\2\2\32"+
		"\u00a3\3\2\2\2\34\u00a5\3\2\2\2\36\u00ae\3\2\2\2 \u00b3\3\2\2\2\"\u00b5"+
		"\3\2\2\2$\u00b9\3\2\2\2&\u00be\3\2\2\2(\u00cb\3\2\2\2*\u00cd\3\2\2\2,"+
		"\u00cf\3\2\2\2.\u00d2\3\2\2\2\60\u00d4\3\2\2\2\62\u00d6\3\2\2\2\64\u00d8"+
		"\3\2\2\2\66\u00da\3\2\2\28\u00dc\3\2\2\2:\u00de\3\2\2\2<\u00e0\3\2\2\2"+
		">\u00e2\3\2\2\2@\u00e4\3\2\2\2B\u00e6\3\2\2\2D\u00e8\3\2\2\2F\u00ea\3"+
		"\2\2\2H\u00ec\3\2\2\2J\u00ee\3\2\2\2LN\5\4\3\2ML\3\2\2\2MN\3\2\2\2NO\3"+
		"\2\2\2OQ\7J\2\2PM\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SV\3\2\2\2TR\3"+
		"\2\2\2UW\5\4\3\2VU\3\2\2\2VW\3\2\2\2WX\3\2\2\2XY\7\2\2\3Y\3\3\2\2\2Z_"+
		"\5\66\34\2[_\5\6\4\2\\_\5\n\6\2]_\5\30\r\2^Z\3\2\2\2^[\3\2\2\2^\\\3\2"+
		"\2\2^]\3\2\2\2_\5\3\2\2\2`b\5\30\r\2a`\3\2\2\2ab\3\2\2\2bc\3\2\2\2ce\5"+
		"\b\5\2df\5\66\34\2ed\3\2\2\2ef\3\2\2\2f\7\3\2\2\2gh\58\35\2hi\5\36\20"+
		"\2ir\3\2\2\2jr\5:\36\2kl\5<\37\2lm\5\32\16\2mr\3\2\2\2no\5> \2op\5\34"+
		"\17\2pr\3\2\2\2qg\3\2\2\2qj\3\2\2\2qk\3\2\2\2qn\3\2\2\2r\t\3\2\2\2st\5"+
		"D#\2tv\5\f\7\2uw\5\66\34\2vu\3\2\2\2vw\3\2\2\2w~\3\2\2\2x~\5\16\b\2y{"+
		"\5J&\2z|\5\66\34\2{z\3\2\2\2{|\3\2\2\2|~\3\2\2\2}s\3\2\2\2}x\3\2\2\2}"+
		"y\3\2\2\2~\13\3\2\2\2\177\u0080\5.\30\2\u0080\r\3\2\2\2\u0081\u0083\5"+
		"\30\r\2\u0082\u0081\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0085\5F$\2\u0085\u0087\5\20\t\2\u0086\u0088\5\66\34\2\u0087\u0086\3"+
		"\2\2\2\u0087\u0088\3\2\2\2\u0088\17\3\2\2\2\u0089\u008e\5\22\n\2\u008a"+
		"\u008b\7\3\2\2\u008b\u008d\5\22\n\2\u008c\u008a\3\2\2\2\u008d\u0090\3"+
		"\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\21\3\2\2\2\u0090"+
		"\u008e\3\2\2\2\u0091\u0097\5\64\33\2\u0092\u0093\7\4\2\2\u0093\u0097\5"+
		"\32\16\2\u0094\u0097\5\24\13\2\u0095\u0097\7\5\2\2\u0096\u0091\3\2\2\2"+
		"\u0096\u0092\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0095\3\2\2\2\u0097\23"+
		"\3\2\2\2\u0098\u0099\5\26\f\2\u0099\u009a\5H%\2\u009a\u009b\7\6\2\2\u009b"+
		"\u009c\5\22\n\2\u009c\u009d\7\7\2\2\u009d\25\3\2\2\2\u009e\u009f\5\64"+
		"\33\2\u009f\27\3\2\2\2\u00a0\u00a1\5\32\16\2\u00a1\u00a2\7\b\2\2\u00a2"+
		"\31\3\2\2\2\u00a3\u00a4\5\62\32\2\u00a4\33\3\2\2\2\u00a5\u00a6\5\64\33"+
		"\2\u00a6\35\3\2\2\2\u00a7\u00af\5 \21\2\u00a8\u00af\5\"\22\2\u00a9\u00af"+
		"\5$\23\2\u00aa\u00af\5&\24\2\u00ab\u00af\5(\25\2\u00ac\u00af\5*\26\2\u00ad"+
		"\u00af\5,\27\2\u00ae\u00a7\3\2\2\2\u00ae\u00a8\3\2\2\2\u00ae\u00a9\3\2"+
		"\2\2\u00ae\u00aa\3\2\2\2\u00ae\u00ab\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae"+
		"\u00ad\3\2\2\2\u00af\37\3\2\2\2\u00b0\u00b4\5.\30\2\u00b1\u00b2\7\4\2"+
		"\2\u00b2\u00b4\5\32\16\2\u00b3\u00b0\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4"+
		"!\3\2\2\2\u00b5\u00b6\7\6\2\2\u00b6\u00b7\5\32\16\2\u00b7\u00b8\7\7\2"+
		"\2\u00b8#\3\2\2\2\u00b9\u00ba\7\6\2\2\u00ba\u00bb\5\32\16\2\u00bb\u00bc"+
		"\7\7\2\2\u00bc\u00bd\7\t\2\2\u00bd%\3\2\2\2\u00be\u00bf\7\n\2\2\u00bf"+
		"\u00c0\7\6\2\2\u00c0\u00c1\5\32\16\2\u00c1\u00c2\7\7\2\2\u00c2\'\3\2\2"+
		"\2\u00c3\u00c4\7\13\2\2\u00c4\u00cc\5\64\33\2\u00c5\u00c6\7\6\2\2\u00c6"+
		"\u00c7\5@!\2\u00c7\u00c8\7\t\2\2\u00c8\u00c9\5\64\33\2\u00c9\u00ca\7\7"+
		"\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00c3\3\2\2\2\u00cb\u00c5\3\2\2\2\u00cc"+
		")\3\2\2\2\u00cd\u00ce\5\32\16\2\u00ce+\3\2\2\2\u00cf\u00d0\7\f\2\2\u00d0"+
		"\u00d1\5\64\33\2\u00d1-\3\2\2\2\u00d2\u00d3\5\64\33\2\u00d3/\3\2\2\2\u00d4"+
		"\u00d5\7I\2\2\u00d5\61\3\2\2\2\u00d6\u00d7\7E\2\2\u00d7\63\3\2\2\2\u00d8"+
		"\u00d9\t\2\2\2\u00d9\65\3\2\2\2\u00da\u00db\7H\2\2\u00db\67\3\2\2\2\u00dc"+
		"\u00dd\t\3\2\2\u00dd9\3\2\2\2\u00de\u00df\t\4\2\2\u00df;\3\2\2\2\u00e0"+
		"\u00e1\t\5\2\2\u00e1=\3\2\2\2\u00e2\u00e3\t\6\2\2\u00e3?\3\2\2\2\u00e4"+
		"\u00e5\7C\2\2\u00e5A\3\2\2\2\u00e6\u00e7\7D\2\2\u00e7C\3\2\2\2\u00e8\u00e9"+
		"\7\r\2\2\u00e9E\3\2\2\2\u00ea\u00eb\7\16\2\2\u00ebG\3\2\2\2\u00ec\u00ed"+
		"\7\20\2\2\u00edI\3\2\2\2\u00ee\u00ef\7\17\2\2\u00efK\3\2\2\2\23MRV^ae"+
		"qv{}\u0082\u0087\u008e\u0096\u00ae\u00b3\u00cb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}