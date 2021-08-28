/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.container.launcher;

import com.maishare.themis.container.provider.ThemisTestCaseProvider;
import com.maishare.themis.context.testcase.ThemisTestCase;

import java.util.Map;

/**
 * themis启动器接口
 * @author lijian
 * @version @Id: ThemisLauncher.java, v 0.1 2020年01月17日 13:43 lijian Exp $
 */
public interface ThemisLauncher {

    /**
     * 启动器预热
     */
    Map<String, ThemisTestCase> preheat();

    /**
     * 激活指定的测试用例
     * @param themisTestCaseProvider 测试用例提供器
     */
    void activateTestCase(ThemisTestCaseProvider themisTestCaseProvider);

}
