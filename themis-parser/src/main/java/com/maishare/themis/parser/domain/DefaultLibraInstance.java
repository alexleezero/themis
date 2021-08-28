/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.domain;

import com.maishare.themis.context.domain.LibraInstance;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.ExtensionLoader;
import com.maishare.themis.parser.executor.LibraExecutor;

import lombok.Getter;

/**
 * libra实例
 * @author moushaokun
 * @version @Id: DefaultLibraInstance.java, v 0.1 2020年03月10日 16:19 moushaokun Exp $
 */
public class DefaultLibraInstance implements LibraInstance {

    @Getter
    private ThemisTestExecution themisTestExecution;

    @Getter
    private String              index;

    public DefaultLibraInstance(ThemisTestExecution themisTestExecution, String index){
        this.themisTestExecution = themisTestExecution;
        this.index = index;
    }

    @Override
    public void run() {
        ExtensionLoader.getExtensionLoader(LibraExecutor.class).getAdaptiveExtension().execute(themisTestExecution);
    }
}

