/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.container.provider;

import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.testcase.ThemisTestCase;

import java.util.List;
import java.util.Map;

/**
 * 测试用例提供器接口
 * @author lijian
 * @version @Id: ThemisTestCaseProvider.java, v 0.1 2020年01月17日 13:47 lijian Exp $
 */
public interface ThemisTestCaseProvider {

    /**
     * 激活指定的测试用例索引
     * @param testCaseIndex 待激活的测试用例索引
     */
    void activateTestCaseIndex(String testCaseIndex);

    /**
     * 激活指定的测试用例索引
     * @param testCaseIndex 待激活的测试用例索引
     */
    void activateTestCaseIndexes(String... testCaseIndex);

    /**
     * 待激活的测试用例索引
     * @param testCaseIndexes 待激活的测试用例索引集合
     */
    void activateTestCaseIndexes(List testCaseIndexes);

    /**
     * 生产出可以执行的测试用例
     */
    Map<String, ThemisTestCase> produce(ThemisContext themisContext);

}
