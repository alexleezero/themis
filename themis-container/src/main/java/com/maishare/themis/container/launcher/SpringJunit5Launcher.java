/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.container.launcher;

import com.maishare.themis.config.ThemisConfig;
import com.maishare.themis.container.provider.SpringTestCaseProvider;
import com.maishare.themis.context.ThemisSpringContext;
import com.maishare.themis.context.testcase.ThemisTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author lijian
 * @version @Id: SpringJunit5Launcher.java, v 0.1 2020年03月02日 13:14 lijian Exp $
 */
public class SpringJunit5Launcher extends Junit5Launcher {

    @Autowired
    private ApplicationContext     applicationContext;

    @Override
    public Map<String, ThemisTestCase> preheat() {
        //主配置初始化
        ThemisConfig themisConfig = new ThemisConfig();

        //初始化spring提供器
        SpringTestCaseProvider themisTestCaseProvider = new
                SpringTestCaseProvider(applicationContext, themisConfig);

        //构造spring上下文
        ThemisSpringContext themisContext = new
                ThemisSpringContext(applicationContext, themisConfig);

        //初始化上下文
        themisContext.init();

        //识别激活的test case
        activateTestCase(themisTestCaseProvider);

        //通过提供者提供用例
        return themisTestCaseProvider.produce(themisContext);
    }
}
