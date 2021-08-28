/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.generator;

import java.util.ArrayList;
import java.util.List;

import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.domain.LibraData;
import com.maishare.themis.context.domain.LibraFile;
import com.maishare.themis.context.domain.LibraInstance;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.context.execution.factory.ThemisTestExecutionFactory;

/**
 * libra实例生成器模板
 * @author moushaokun
 * @version @Id: AbstractLibraInstanceGenerator.java, v 0.1 2020年03月17日 16:04 moushaokun Exp $
 */
public abstract class AbstractLibraInstanceGenerator implements LibraInstanceGenerator {

    @Override
    public List<LibraInstance> generate(ThemisContext themisContext, LibraFile libraFile) {
        List<LibraInstance> libraInstances = new ArrayList<>();

        List<LibraData> dataCases = assembleLibraData(libraFile);

        dataCases.forEach(libraDataCase ->
                libraInstances.add(assembleInstance(assembleThemisTestExecution(themisContext, libraFile, libraDataCase), libraDataCase)));

        return libraInstances;
    }

    public ThemisTestExecution assembleThemisTestExecution(ThemisContext themisContext, LibraFile libraFile, LibraData libraData){
        ThemisTestExecution execution = ThemisTestExecutionFactory.create(themisContext);

        execution.setLibraData(libraData);
        execution.setLibraFile(libraFile);
        execution.setThemisContext(themisContext);

        return execution;
    }

    public abstract LibraInstance assembleInstance(ThemisTestExecution execution, LibraData libraData);

    public abstract List<LibraData> assembleLibraData(LibraFile libraFile);
}

