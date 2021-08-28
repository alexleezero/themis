/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.factory;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.maishare.themis.component.param.ExecuteBeanCompParam;
import com.maishare.themis.context.component.LibraComponentParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.parser.libra.LibraParser;

/**
 * 执行组件参数工厂
 * @author moushaokun
 * @version @Id: ExecuteCompParamFactory.java, v 0.1 2020年03月31日 13:59 moushaokun Exp $
 */
public class ExecuteCompParamFactory extends AbstractLibraComponentParamFactory{
    @Override
    public List<String> getKeys() {
        return Lists.newArrayList("execute_bean", "execute_class");
    }

    @Override
    public LibraComponentParam createParam(LibraParser.CompDeclContext context, ThemisTestExecution themisTestExecution, Map<String, Object> params) {
        String beanName = getStringValue(context,0, themisTestExecution, params);
        String method = getStringValue(context,1, themisTestExecution, params);
        return new ExecuteBeanCompParam(beanName, method);
    }
}

