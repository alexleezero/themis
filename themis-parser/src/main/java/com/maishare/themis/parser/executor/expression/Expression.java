/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.expression;

import com.maishare.themis.context.execution.ThemisTestExecution;

import java.util.Map;

/**
 * libra表达式接口
 * @author moushaokun
 * @version @Id: Expression.java, v 0.1 2020年03月11日 14:32 moushaokun Exp $
 */
public interface Expression {

    /**
     * 执行语句
     */
    void evaluate(ThemisTestExecution themisTestExecution, Map<String, Object> params);
}

