/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.factory;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.maishare.themis.component.param.ReferenceCompParam;
import com.maishare.themis.context.component.LibraComponentParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.parser.libra.LibraParser;

/**
 * 引用组件参数工厂
 * @author moushaokun
 * @version @Id: ReferenceCompParamFactory.java, v 0.1 2020年03月31日 14:01 moushaokun Exp $
 */
public class ReferenceCompParamFactory extends AbstractLibraComponentParamFactory {
    @Override
    public List<String> getKeys() {
        return Lists.newArrayList("freestyle_reference");
    }

    @Override
    public LibraComponentParam createParam(LibraParser.CompDeclContext context, ThemisTestExecution themisTestExecution, Map<String, Object> params) {
        return new ReferenceCompParam(getStringValue(context, 0, themisTestExecution, params));
    }
}

