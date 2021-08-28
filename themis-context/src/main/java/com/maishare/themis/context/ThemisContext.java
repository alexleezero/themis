/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context;

import com.maishare.themis.config.ThemisConfig;
import com.maishare.themis.context.registry.LibraCompRegistry;
import com.maishare.themis.context.testcase.ThemisTestCase;

import java.util.Map;

/**
 * themis上下文接口
 * @author lijian
 * @version @Id: ThemisContext.java, v 0.1 2020年01月17日 13:54 lijian Exp $
 */
public interface ThemisContext {

    /**
     * 初始化
     */
    void init();

    /**
     * 填充themis测试用例
     */
    void populateTestCases(Map<String, ThemisTestCase> themisTestCases);

    /**
     * 获取themis测试用例
     */
    Map<String, ThemisTestCase> getTestCases();

    /**
     * 获取配置
     */
    ThemisConfig getThemisConfig();

    /**
     * 获取组件注册器
     */
    LibraCompRegistry getLibraCompRegistry();
    
    /**
     *
     * @param beanName 全限定名
     */
     <T> T  getBean(String beanName);

}
