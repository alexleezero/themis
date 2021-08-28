// Generated from D:/workspaces/m-bravo/themis/themis/themis-parser/src/main/java/com/maishare/themis/parser/libra\Libra.g4 by ANTLR 4.8
package com.maishare.themis.parser.libra;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LibraParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LibraVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LibraParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(LibraParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(LibraParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(LibraParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(LibraParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(LibraParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#superCompDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperCompDecl(LibraParser.SuperCompDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#compDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompDecl(LibraParser.CompDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#compType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompType(LibraParser.CompTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#compName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompName(LibraParser.CompNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(LibraParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#conditionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionDecl(LibraParser.ConditionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#realBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealBlock(LibraParser.RealBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#fakeBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFakeBlock(LibraParser.FakeBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#conditionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionExpr(LibraParser.ConditionExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#followedCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFollowedCondition(LibraParser.FollowedConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#contrastCondExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContrastCondExpr(LibraParser.ContrastCondExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#conditionOps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionOps(LibraParser.ConditionOpsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(LibraParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#externalVarName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternalVarName(LibraParser.ExternalVarNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link LibraParser#digits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigits(LibraParser.DigitsContext ctx);
}