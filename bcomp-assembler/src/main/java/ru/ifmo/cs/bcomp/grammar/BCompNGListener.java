// Generated from d:\programming\bevm-2\BasicComputerExt\bcomp-assembler\src\main\java\ru\ifmo\cs\bcomp\grammar\BCompNG.g4 by ANTLR 4.8
package ru.ifmo.cs.bcomp.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BCompNGParser}.
 */
public interface BCompNGListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(BCompNGParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(BCompNGParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(BCompNGParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(BCompNGParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#instructionLine}.
	 * @param ctx the parse tree
	 */
	void enterInstructionLine(BCompNGParser.InstructionLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#instructionLine}.
	 * @param ctx the parse tree
	 */
	void exitInstructionLine(BCompNGParser.InstructionLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(BCompNGParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(BCompNGParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#directive}.
	 * @param ctx the parse tree
	 */
	void enterDirective(BCompNGParser.DirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#directive}.
	 * @param ctx the parse tree
	 */
	void exitDirective(BCompNGParser.DirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#orgAddress}.
	 * @param ctx the parse tree
	 */
	void enterOrgAddress(BCompNGParser.OrgAddressContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#orgAddress}.
	 * @param ctx the parse tree
	 */
	void exitOrgAddress(BCompNGParser.OrgAddressContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#wordDirective}.
	 * @param ctx the parse tree
	 */
	void enterWordDirective(BCompNGParser.WordDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#wordDirective}.
	 * @param ctx the parse tree
	 */
	void exitWordDirective(BCompNGParser.WordDirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#wordArguments}.
	 * @param ctx the parse tree
	 */
	void enterWordArguments(BCompNGParser.WordArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#wordArguments}.
	 * @param ctx the parse tree
	 */
	void exitWordArguments(BCompNGParser.WordArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#wordArgument}.
	 * @param ctx the parse tree
	 */
	void enterWordArgument(BCompNGParser.WordArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#wordArgument}.
	 * @param ctx the parse tree
	 */
	void exitWordArgument(BCompNGParser.WordArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#dupArgument}.
	 * @param ctx the parse tree
	 */
	void enterDupArgument(BCompNGParser.DupArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#dupArgument}.
	 * @param ctx the parse tree
	 */
	void exitDupArgument(BCompNGParser.DupArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#count}.
	 * @param ctx the parse tree
	 */
	void enterCount(BCompNGParser.CountContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#count}.
	 * @param ctx the parse tree
	 */
	void exitCount(BCompNGParser.CountContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#lbl}.
	 * @param ctx the parse tree
	 */
	void enterLbl(BCompNGParser.LblContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#lbl}.
	 * @param ctx the parse tree
	 */
	void exitLbl(BCompNGParser.LblContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(BCompNGParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(BCompNGParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#dev}.
	 * @param ctx the parse tree
	 */
	void enterDev(BCompNGParser.DevContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#dev}.
	 * @param ctx the parse tree
	 */
	void exitDev(BCompNGParser.DevContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(BCompNGParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(BCompNGParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#directAbsolute}.
	 * @param ctx the parse tree
	 */
	void enterDirectAbsolute(BCompNGParser.DirectAbsoluteContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#directAbsolute}.
	 * @param ctx the parse tree
	 */
	void exitDirectAbsolute(BCompNGParser.DirectAbsoluteContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#indirect}.
	 * @param ctx the parse tree
	 */
	void enterIndirect(BCompNGParser.IndirectContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#indirect}.
	 * @param ctx the parse tree
	 */
	void exitIndirect(BCompNGParser.IndirectContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#postIncrement}.
	 * @param ctx the parse tree
	 */
	void enterPostIncrement(BCompNGParser.PostIncrementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#postIncrement}.
	 * @param ctx the parse tree
	 */
	void exitPostIncrement(BCompNGParser.PostIncrementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#preDecrement}.
	 * @param ctx the parse tree
	 */
	void enterPreDecrement(BCompNGParser.PreDecrementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#preDecrement}.
	 * @param ctx the parse tree
	 */
	void exitPreDecrement(BCompNGParser.PreDecrementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#displacementSP}.
	 * @param ctx the parse tree
	 */
	void enterDisplacementSP(BCompNGParser.DisplacementSPContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#displacementSP}.
	 * @param ctx the parse tree
	 */
	void exitDisplacementSP(BCompNGParser.DisplacementSPContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#directRelative}.
	 * @param ctx the parse tree
	 */
	void enterDirectRelative(BCompNGParser.DirectRelativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#directRelative}.
	 * @param ctx the parse tree
	 */
	void exitDirectRelative(BCompNGParser.DirectRelativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#directLoad}.
	 * @param ctx the parse tree
	 */
	void enterDirectLoad(BCompNGParser.DirectLoadContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#directLoad}.
	 * @param ctx the parse tree
	 */
	void exitDirectLoad(BCompNGParser.DirectLoadContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#address}.
	 * @param ctx the parse tree
	 */
	void enterAddress(BCompNGParser.AddressContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#address}.
	 * @param ctx the parse tree
	 */
	void exitAddress(BCompNGParser.AddressContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(BCompNGParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(BCompNGParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(BCompNGParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(BCompNGParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(BCompNGParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(BCompNGParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(BCompNGParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(BCompNGParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#addr}.
	 * @param ctx the parse tree
	 */
	void enterAddr(BCompNGParser.AddrContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#addr}.
	 * @param ctx the parse tree
	 */
	void exitAddr(BCompNGParser.AddrContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#nonaddr}.
	 * @param ctx the parse tree
	 */
	void enterNonaddr(BCompNGParser.NonaddrContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#nonaddr}.
	 * @param ctx the parse tree
	 */
	void exitNonaddr(BCompNGParser.NonaddrContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#branch}.
	 * @param ctx the parse tree
	 */
	void enterBranch(BCompNGParser.BranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#branch}.
	 * @param ctx the parse tree
	 */
	void exitBranch(BCompNGParser.BranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#io}.
	 * @param ctx the parse tree
	 */
	void enterIo(BCompNGParser.IoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#io}.
	 * @param ctx the parse tree
	 */
	void exitIo(BCompNGParser.IoContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#sp}.
	 * @param ctx the parse tree
	 */
	void enterSp(BCompNGParser.SpContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#sp}.
	 * @param ctx the parse tree
	 */
	void exitSp(BCompNGParser.SpContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#ip}.
	 * @param ctx the parse tree
	 */
	void enterIp(BCompNGParser.IpContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#ip}.
	 * @param ctx the parse tree
	 */
	void exitIp(BCompNGParser.IpContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#org}.
	 * @param ctx the parse tree
	 */
	void enterOrg(BCompNGParser.OrgContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#org}.
	 * @param ctx the parse tree
	 */
	void exitOrg(BCompNGParser.OrgContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#word}.
	 * @param ctx the parse tree
	 */
	void enterWord(BCompNGParser.WordContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#word}.
	 * @param ctx the parse tree
	 */
	void exitWord(BCompNGParser.WordContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#dup}.
	 * @param ctx the parse tree
	 */
	void enterDup(BCompNGParser.DupContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#dup}.
	 * @param ctx the parse tree
	 */
	void exitDup(BCompNGParser.DupContext ctx);
	/**
	 * Enter a parse tree produced by {@link BCompNGParser#end}.
	 * @param ctx the parse tree
	 */
	void enterEnd(BCompNGParser.EndContext ctx);
	/**
	 * Exit a parse tree produced by {@link BCompNGParser#end}.
	 * @param ctx the parse tree
	 */
	void exitEnd(BCompNGParser.EndContext ctx);
}