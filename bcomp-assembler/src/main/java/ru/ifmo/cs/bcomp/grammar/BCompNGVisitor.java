// Generated from d:\programming\bevm-2\BasicComputerExt\bcomp-assembler\src\main\java\ru\ifmo\cs\bcomp\grammar\BCompNG.g4 by ANTLR 4.8
package ru.ifmo.cs.bcomp.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BCompNGParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BCompNGVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(BCompNGParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(BCompNGParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#instructionLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructionLine(BCompNGParser.InstructionLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(BCompNGParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirective(BCompNGParser.DirectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#orgAddress}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrgAddress(BCompNGParser.OrgAddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#wordDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWordDirective(BCompNGParser.WordDirectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#wordArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWordArguments(BCompNGParser.WordArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#wordArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWordArgument(BCompNGParser.WordArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#dupArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDupArgument(BCompNGParser.DupArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCount(BCompNGParser.CountContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#lbl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLbl(BCompNGParser.LblContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(BCompNGParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#dev}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDev(BCompNGParser.DevContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#operand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperand(BCompNGParser.OperandContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#directAbsolute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectAbsolute(BCompNGParser.DirectAbsoluteContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#indirect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndirect(BCompNGParser.IndirectContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#postIncrement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIncrement(BCompNGParser.PostIncrementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#preDecrement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreDecrement(BCompNGParser.PreDecrementContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#displacementSP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisplacementSP(BCompNGParser.DisplacementSPContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#directRelative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectRelative(BCompNGParser.DirectRelativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#directLoad}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectLoad(BCompNGParser.DirectLoadContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#address}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddress(BCompNGParser.AddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(BCompNGParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(BCompNGParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(BCompNGParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(BCompNGParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#addr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddr(BCompNGParser.AddrContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#nonaddr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonaddr(BCompNGParser.NonaddrContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(BCompNGParser.BranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#io}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIo(BCompNGParser.IoContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#sp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSp(BCompNGParser.SpContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#ip}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIp(BCompNGParser.IpContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#org}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrg(BCompNGParser.OrgContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#word}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWord(BCompNGParser.WordContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#dup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDup(BCompNGParser.DupContext ctx);
	/**
	 * Visit a parse tree produced by {@link BCompNGParser#end}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd(BCompNGParser.EndContext ctx);
}