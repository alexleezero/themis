/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.executor.factory;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.maishare.themis.component.param.PrintConsoleCompParam;
import com.maishare.themis.context.component.LibraComponentParam;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.parser.libra.LibraParser;

/**
 * 打印组件参数工厂
 * @author moushaokun
 * @version @Id: PrintCompParamFactory.java, v 0.1 2020年03月31日 14:02 moushaokun Exp $
 */
public class PrintCompParamFactory extends AbstractLibraComponentParamFactory {
    @Override
    public List<String> getKeys() {
        return Lists.newArrayList("print_console");
    }

    @Override
    public LibraComponentParam createParam(LibraParser.CompDeclContext context, ThemisTestExecution themisTestExecution, Map<String, Object> params) {
        return new PrintConsoleCompParam(getStringValue(context, 0, themisTestExecution, params));
    }
}

