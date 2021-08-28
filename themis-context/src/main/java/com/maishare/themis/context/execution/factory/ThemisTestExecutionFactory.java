/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.execution.factory;

import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.ThemisSpringContext;
import com.maishare.themis.context.execution.DefaultTestExecution;
import com.maishare.themis.context.execution.SpringTestExecution;
import com.maishare.themis.context.execution.ThemisTestExecution;

/**
 * ThemisTestExecution 工厂
 * @author moushaokun
 * @version @Id: ThemisTestExecutionFactory.java, v 0.1 2020年03月12日 10:53 moushaokun Exp $
 */
public final class ThemisTestExecutionFactory {

    private ThemisTestExecutionFactory(){}

    public static ThemisTestExecution create(ThemisContext themisContext) {
        if (themisContext instanceof ThemisSpringContext) {
            return new SpringTestExecution(((ThemisSpringContext)themisContext).getApplicationContext());
        } else {
            return new DefaultTestExecution();
        }
    }
}

