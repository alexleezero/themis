/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maishare.themis.common.constants.LibraCompNameConstants;
import com.maishare.themis.component.param.PrintConsoleCompParam;
import com.maishare.themis.context.execution.ThemisTestExecution;

/**
 * Print console 组件
 * @author moushaokun
 * @version @Id: PrintConsoleComp.java, v 0.1 2020年03月11日 14:14 moushaokun Exp $
 */
public class PrintConsoleComp extends PrintComponent<PrintConsoleCompParam> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintConsoleComp.class);

    @Override
    public void act(ThemisTestExecution themisTestExecution, PrintConsoleCompParam printCompParam) {
        LOGGER.debug(printCompParam.getContent());
    }

    @Override
    public String name() {
        return LibraCompNameConstants.CONSOLE;
    }
}

