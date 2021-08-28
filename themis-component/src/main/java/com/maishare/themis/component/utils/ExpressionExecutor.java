/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.utils;

import java.util.Map;

import org.springframework.expression.ExpressionException;

import com.googlecode.aviator.AviatorEvaluator;

/**
 * 表达式执行器
 * @author moushaokun
 * @version @Id: ExpressionExecutor.java, v 0.1 2020年03月13日 09:54 moushaokun Exp $
 */
public class ExpressionExecutor {

    private ExpressionExecutor(){}

    /**
     * 解析表达式
     * @param expression    表达式
     * @param params        表达式中包含的参数值
     * @return  解析值
     */
    public static Object execute(String expression, Map<String, Object> params){
        Object value;
        try {
            value = AviatorEvaluator.execute(expression, params);
        } catch (Exception e){
            throw new ExpressionException(String.format("表达式解析错误：%s", expression), e);
        }
        return value;
    }

}

