/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.testcase;

import com.maishare.themis.context.domain.LibraInstance;
import com.maishare.themis.extension.ExtensionLoader;

import java.util.Set;

/**
 * 默认的测试用例模型
 * @author lijian
 * @version @Id: DefaultTestCase.java, v 0.1 2020年03月02日 13:30 lijian Exp $
 */
public class DefaultTestCase implements ThemisTestCase {

    /** 用例名称 */
    protected String                        displayName;
    protected LibraInstance                 libraInstance;
    
    protected static Set<LibraInstancePostProcessor> libraInstancePostProcessorList = ExtensionLoader.getExtensionLoader(LibraInstancePostProcessor.class).getSupportedExtensionInstances();
    
    
    public DefaultTestCase(String displayName, LibraInstance libraInstance) {
        this.displayName = displayName;
        this.libraInstance = libraInstance;
    }

    @Override
    public String displayName() {
        return this.displayName;
    }

    @Override
    public void execute() {
        try {
            libraInstance.run();
        } finally {
            postProcess();
        }
    }
    
    private void postProcess() {
        for (LibraInstancePostProcessor libraInstancePostProcessor : libraInstancePostProcessorList) {
            libraInstancePostProcessor.postProcess(libraInstance);
        }
    }
}
