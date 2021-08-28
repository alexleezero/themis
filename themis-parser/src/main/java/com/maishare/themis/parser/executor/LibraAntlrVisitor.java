/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor;

import com.google.common.collect.Maps;
import com.maishare.themis.common.exception.LibraExpressionException;
import com.maishare.themis.component.utils.ExpressionExecutor;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.parser.executor.context.ExecuteContext;
import com.maishare.themis.parser.executor.context.ExecuteItem;
import com.maishare.themis.parser.executor.expression.AssignExpression;
import com.maishare.themis.parser.executor.expression.CompExpression;
import com.maishare.themis.parser.executor.expression.Expression;
import com.maishare.themis.parser.libra.LibraBaseVisitor;
import com.maishare.themis.parser.libra.LibraParser;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * libra antlr 访问控制器
 * @author moushaokun
 * @version @Id: LibraAntlrVisitor.java, v 0.1 2020年03月12日 18:49 moushaokun Exp $
 */
public class LibraAntlrVisitor extends LibraBaseVisitor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LibraAntlrVisitor.class);

    private ThemisTestExecution themisTestExecution;

    /** 逻辑文件执行过程中的内部参数 */
    private Map<String, Object> params = new HashMap<>();

    public LibraAntlrVisitor(ThemisTestExecution themisTestExecution) {
        this.themisTestExecution = themisTestExecution;
    }

    @Override
    public Object visitVarDecl(LibraParser.VarDeclContext ctx) {
        execute(new AssignExpression(ctx), ctx.getText(), themisTestExecution, params);

        return super.visitVarDecl(ctx);
    }

    @Override
    public Object visitSuperCompDecl(LibraParser.SuperCompDeclContext ctx) {
        ExecuteContext localContext = LibraAntlrExecutor.getReservedContext().get();
        localContext.getContexts().add(new ExecuteItem( new CompExpression(ctx.compDecl()), themisTestExecution, Maps.newHashMap(params)));

        return false;
    }

    @Override
    public Object visitCompDecl(LibraParser.CompDeclContext ctx) {
        execute(new CompExpression(ctx), ctx.getText(), themisTestExecution, params);

        return super.visitCompDecl(ctx);
    }

    @Override
    public Object visitConditionDecl(LibraParser.ConditionDeclContext ctx) {

        ExecuteContext localContext = LibraAntlrExecutor.getReservedContext().get();

        if (!localContext.isErrorOccurs()) {

            String expr = ctx.conditionExpr().getText();

            //执行表达式
            Boolean value = Boolean.valueOf(ExpressionExecutor.execute(expr, params).toString());

            if (value) {
                //表达式成立
                ctx.realBlock().forEach(this::visit);
            } else {
                //表达式不成立
                ctx.fakeBlock().forEach(this::visit);
            }
        }

        return false;
    }

    @Override
    public Object visitErrorNode(ErrorNode node) {
        throw new LibraExpressionException(String.format("解析错误，错误行：%s", node.getText()));
    }


    private void execute(Expression expression, String text, ThemisTestExecution themisTestExecution, Map<String, Object> params){
        ExecuteContext localContext = LibraAntlrExecutor.getReservedContext().get();

        if (!localContext.isErrorOccurs()){
            try{
                expression.evaluate(themisTestExecution, params);
            } catch (Throwable e) {
                localContext.setErrorOccurs(true);
                localContext.setThrowable(e);
                LOGGER.error(String.format("执行操作[%s]时出现错误，将跳过后续步骤，直接执行预留操作", text), e);
            }
        }
    }

}

