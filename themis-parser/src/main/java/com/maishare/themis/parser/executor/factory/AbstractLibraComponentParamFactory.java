/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.factory;

import java.util.List;
import java.util.Map;

import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.parser.libra.LibraParser;
import com.maishare.themis.parser.support.RuleContextExpressionExecutor;

/**
 * libra组件参数工厂模板
 * @author moushaokun
 * @version @Id: AbstractLibraComponentParamFactory.java, v 0.1 2020年03月31日 00:27 moushaokun Exp $
 */
public abstract class AbstractLibraComponentParamFactory implements LibraComponentParamFactory {

    @Override
    public boolean support(String compTypeCode, String compName) {
        return getKeys().contains(compTypeCode + "_" + compName);
    }

    public abstract List<String> getKeys();


    public String getStringValue(LibraParser.CompDeclContext context,
                                         int index,
                                         ThemisTestExecution themisTestExecution,
                                         Map<String, Object> params){
        Object value;

        try{
            value = RuleContextExpressionExecutor.execute(context.params().expr(index), params, themisTestExecution);
        } catch (Exception e) {
            throw new ComponentException(String.format("调用[%s]组件失败：参数不匹配", context.compType().getText()), e);
        }

        return value == null ? null : value.toString();
    }
}
