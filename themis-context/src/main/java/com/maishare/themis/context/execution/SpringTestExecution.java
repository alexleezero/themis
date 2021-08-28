/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.execution;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;

/**
 * spring执行逻辑
 * @author lijian
 * @version @Id: SpringTestExecution.java, v 0.1 2020年03月02日 15:02 lijian Exp $
 */
public class SpringTestExecution extends DefaultTestExecution {

    @Getter
    @Setter
    private ApplicationContext applicationContext;

    public SpringTestExecution(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
