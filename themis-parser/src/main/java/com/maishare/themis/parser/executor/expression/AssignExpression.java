/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.expression;

import java.util.Map;

import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.parser.libra.LibraParser;
import com.maishare.themis.parser.support.RuleContextExpressionExecutor;

/**
 * 赋值表达式
 * @author moushaokun
 * @version @Id: AssginExpression.java, v 0.1 2020年03月11日 18:01 moushaokun Exp $
 */
public class AssignExpression extends AbstractExpression {

    private LibraParser.VarDeclContext context;

    public AssignExpression(LibraParser.VarDeclContext context) {
        this.context = context;
    }

    @Override
    public void evaluate(ThemisTestExecution themisTestExecution, Map<String, Object> params) {
        Object value = RuleContextExpressionExecutor.execute(context.expr(), params, themisTestExecution);

        if(value != null)params.put(this.context.ID().getText(), value);
    }

}

