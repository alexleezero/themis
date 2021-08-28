/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.container.provider;

import org.springframework.context.ApplicationContext;

import com.maishare.themis.config.ThemisConfig;

import lombok.Getter;

/**
 * spring环境测试用例提供器
 * @author lijian
 * @version @Id: SpringTestCaseProvider.java, v 0.1 2020年03月02日 13:20 lijian Exp $
 */
public class SpringTestCaseProvider extends DefaultTestCaseProvider {

    @Getter
    private ApplicationContext applicationContext;

    public SpringTestCaseProvider(ApplicationContext applicationContext, ThemisConfig themisConfig) {
        super(themisConfig);
        this.applicationContext = applicationContext;
    }

}
