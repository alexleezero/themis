/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.container.launcher;

import com.maishare.themis.config.ThemisConfig;
import com.maishare.themis.container.provider.DefaultTestCaseProvider;
import com.maishare.themis.container.provider.ThemisTestCaseProvider;
import com.maishare.themis.context.ThemisCommonContext;
import com.maishare.themis.context.testcase.ThemisTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * junit5 启动器
 * @author lijian
 * @version @Id: Junit5Launcher.java, v 0.1 2020年01月17日 13:49 lijian Exp $
 */
public class Junit5Launcher implements ThemisLauncher {

    /**
     * junit5 启动中心
     */
    @TestFactory
    @DisplayName("Themis测试开启")
    public Iterable<DynamicTest> themisJunitFactory() {
        //1. 预热测试引擎从中得到测试用例集合
        Map<String, ThemisTestCase> testCaseMap = this.preheat();

        //将themis测试用例转换成junit5测试用例
        return convertDynamicTest(testCaseMap);
    }

    /**
     * {@link ThemisLauncher#preheat()}
     */
    @Override
    public Map<String, ThemisTestCase> preheat() {
        //主配置初始化
        ThemisConfig themisConfig = new ThemisConfig();

        //初始化提供器
        DefaultTestCaseProvider themisTestCaseProvider = new DefaultTestCaseProvider(themisConfig);

        //构造通用上下文
        ThemisCommonContext themisContext = new ThemisCommonContext(themisConfig);

        //初始化上下文
        themisContext.init();

        //识别激活的test case
        activateTestCase(themisTestCaseProvider);

        //通过提供者提供用例
        return themisTestCaseProvider.produce(themisContext);
    }

    @Override
    public void activateTestCase(ThemisTestCaseProvider themisTestCaseProvider) {
        // parent do nothing
    }




    //~~~ 私有方法

    /**
     * 将themis测试用例转换成junit5测试用例
     * @param testCases
     */
    private Iterable<DynamicTest> convertDynamicTest(Map<String, ThemisTestCase> testCases) {

        return testCases.entrySet().stream().map(testCase ->
                DynamicTest.dynamicTest(testCase.getValue().displayName(),
                        testCase.getValue()::execute))
                .collect(Collectors.toList());
    }
}
