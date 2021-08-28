// Generated from D:/workspaces/m-bravo/themis/themis/themis-parser/src/main/java/com/maishare/themis/parser/libra\Libra.g4 by ANTLR 4.8
package com.maishare.themis.parser.libra;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LibraParser}.
 */
public interface LibraListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LibraParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(LibraParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(LibraParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(LibraParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(LibraParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(LibraParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(LibraParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(LibraParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(LibraParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(LibraParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(LibraParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#superCompDecl}.
	 * @param ctx the parse tree
	 */
	void enterSuperCompDecl(LibraParser.SuperCompDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#superCompDecl}.
	 * @param ctx the parse tree
	 */
	void exitSuperCompDecl(LibraParser.SuperCompDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#compDecl}.
	 * @param ctx the parse tree
	 */
	void enterCompDecl(LibraParser.CompDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#compDecl}.
	 * @param ctx the parse tree
	 */
	void exitCompDecl(LibraParser.CompDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#compType}.
	 * @param ctx the parse tree
	 */
	void enterCompType(LibraParser.CompTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#compType}.
	 * @param ctx the parse tree
	 */
	void exitCompType(LibraParser.CompTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#compName}.
	 * @param ctx the parse tree
	 */
	void enterCompName(LibraParser.CompNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#compName}.
	 * @param ctx the parse tree
	 */
	void exitCompName(LibraParser.CompNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(LibraParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(LibraParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#conditionDecl}.
	 * @param ctx the parse tree
	 */
	void enterConditionDecl(LibraParser.ConditionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#conditionDecl}.
	 * @param ctx the parse tree
	 */
	void exitConditionDecl(LibraParser.ConditionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#realBlock}.
	 * @param ctx the parse tree
	 */
	void enterRealBlock(LibraParser.RealBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#realBlock}.
	 * @param ctx the parse tree
	 */
	void exitRealBlock(LibraParser.RealBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#fakeBlock}.
	 * @param ctx the parse tree
	 */
	void enterFakeBlock(LibraParser.FakeBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#fakeBlock}.
	 * @param ctx the parse tree
	 */
	void exitFakeBlock(LibraParser.FakeBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#conditionExpr}.
	 * @param ctx the parse tree
	 */
	void enterConditionExpr(LibraParser.ConditionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#conditionExpr}.
	 * @param ctx the parse tree
	 */
	void exitConditionExpr(LibraParser.ConditionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#followedCondition}.
	 * @param ctx the parse tree
	 */
	void enterFollowedCondition(LibraParser.FollowedConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#followedCondition}.
	 * @param ctx the parse tree
	 */
	void exitFollowedCondition(LibraParser.FollowedConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#contrastCondExpr}.
	 * @param ctx the parse tree
	 */
	void enterContrastCondExpr(LibraParser.ContrastCondExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#contrastCondExpr}.
	 * @param ctx the parse tree
	 */
	void exitContrastCondExpr(LibraParser.ContrastCondExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#conditionOps}.
	 * @param ctx the parse tree
	 */
	void enterConditionOps(LibraParser.ConditionOpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#conditionOps}.
	 * @param ctx the parse tree
	 */
	void exitConditionOps(LibraParser.ConditionOpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(LibraParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(LibraParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#externalVarName}.
	 * @param ctx the parse tree
	 */
	void enterExternalVarName(LibraParser.ExternalVarNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#externalVarName}.
	 * @param ctx the parse tree
	 */
	void exitExternalVarName(LibraParser.ExternalVarNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibraParser#digits}.
	 * @param ctx the parse tree
	 */
	void enterDigits(LibraParser.DigitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibraParser#digits}.
	 * @param ctx the parse tree
	 */
	void exitDigits(LibraParser.DigitsContext ctx);
}