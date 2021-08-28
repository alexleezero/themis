/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.maishare.themis.component.utils.ExpressionExecutor;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.parser.libra.LibraParser;

/**
 * 语法上下文表达式执行器
 * @author moushaokun
 * @version @Id: RuleContextExpressionExecutor.java, v 0.1 2020年03月13日 09:54 moushaokun Exp $
 */
public class RuleContextExpressionExecutor {

    private RuleContextExpressionExecutor(){}

    /**
     * 解析表达式
     * @param exprContext           表达式节点上下文
     * @param params                表达式中包含的参数值，参数为libra逻辑文件中定义的常量或常量表达式
     * @param themisTestExecution   themis上下文，用于解析表达式中包含的外部参数值，参数为libra数据文件中定义的属性
     * @return  解析值
     */
    public static Object execute(LibraParser.ExprContext exprContext,
                                 Map<String, Object> params,
                                 ThemisTestExecution themisTestExecution) {
        Object value = null;

        if (exprContext.digits() != null) {//处理数字类型赋值操作
            value = Integer.valueOf(exprContext.digits().getText());

        } else if (exprContext.FALSE() != null || exprContext.TRUE() != null) {
            value = Boolean.valueOf(exprContext.getText());

        } else if(exprContext.ID() != null) {//处理引用类型赋值操作
            value = params.get(exprContext.ID().getText());

        } else if(exprContext.STRING() != null) {//处理字符串类型赋值操作
            value = exprContext.STRING().getText();

            if(value!=null && ((String) value).startsWith("\"")) value = value.toString().substring(1);
            if(value!=null && ((String) value).endsWith("\"")) value = value.toString().substring(0, value.toString().length()-1);

        } else if (exprContext.externalVarName() != null) {//处理外部属性引用
            value = ExpressionExecutor.execute(exprContext.externalVarName().getText().substring(1), themisTestExecution.getLibraData().getData());

        } else if(exprContext.expr() != null) {//处理表达式类型赋值操作：此操作一定是在最后执行，因为表达式节点包含以上所有种类的节点
            String expr = exprContext.getText();

            //如果有外部变量，将外部变量转变成内部变量进行计算
            if (expr.contains("$")) {
                HashMap<String, Object> innerParam = Maps.newHashMap(params);

                String replacedExpr = replaceAllExternalVarNames(expr, exprContext.expr(), innerParam, themisTestExecution.getLibraData().getData());

                value = ExpressionExecutor.execute(replacedExpr, innerParam);
            } else {
                value = ExpressionExecutor.execute(expr, params);
            }
        }

        return value;
    }


    private static String replaceAllExternalVarNames(String expr, List<LibraParser.ExprContext> exprContexts,
                                            Map<String, Object> innerParams, Map<String, Object> externalParams){
        for (LibraParser.ExprContext exprContext : exprContexts) {
            if(!expr.contains("$")){
                break;
            }
            if (exprContext.externalVarName() != null) {
                String paramName = exprContext.externalVarName().getText().substring(1);
                String realParamName = "external_" + paramName;
                expr = expr.replaceAll("\\$" + paramName, realParamName);

                innerParams.put(realParamName, externalParams.get(paramName));
            } else if (exprContext.expr() != null){
                expr = replaceAllExternalVarNames(expr, exprContext.expr(), innerParams, externalParams);
            }
        }

        return expr;
    }

}

