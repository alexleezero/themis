/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.testcase;

import com.maishare.themis.context.domain.LibraInstance;
import lombok.Getter;
import org.springframework.context.ApplicationContext;

/**
 * @author lijian
 * @version @Id: SpringTestCase.java, v 0.1 2020年03月02日 13:33 lijian Exp $
 */
public class SpringTestCase extends DefaultTestCase {

    @Getter
    private ApplicationContext applicationContext;
    
    public SpringTestCase(String displayName, LibraInstance libraInstance,
                          ApplicationContext applicationContext) {
        super(displayName, libraInstance);
        this.applicationContext = applicationContext;
    }
}
