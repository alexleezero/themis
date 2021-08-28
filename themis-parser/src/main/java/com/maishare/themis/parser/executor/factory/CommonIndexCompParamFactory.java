/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.factory;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.maishare.themis.component.param.CommonIndexCompParam;
import com.maishare.themis.context.component.LibraComponentParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.parser.libra.LibraParser;

/**
 * 公用组件调用参数工厂
 * @author moushaokun
 * @version @Id: CommonIndexCompParamFactory.java, v 0.1 2020年03月31日 13:29 moushaokun Exp $
 */
public class CommonIndexCompParamFactory extends AbstractLibraComponentParamFactory {

    @Override
    public LibraComponentParam createParam(LibraParser.CompDeclContext context, ThemisTestExecution themisTestExecution, Map<String, Object> params) {
        String fileName = getStringValue(context, 0, themisTestExecution, params);
        String index = getStringValue(context, 1, themisTestExecution, params);
        return new CommonIndexCompParam(fileName, index);
    }

    @Override
    public List<String> getKeys() {
        return Lists.newArrayList("check_db", "check_yaml", "clear_db", "clear_redis", "mock_yaml", "prepare_db", "prepare_yaml");
    }
}

