/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.test;

import com.maishare.themis.container.launcher.Junit5Launcher;
import com.maishare.themis.container.provider.ThemisTestCaseProvider;

/**
 * 非spring环境测试用例
 * @author moushaokun
 * @version @Id: DefaultTest.java, v 0.1 2020年03月25日 17:44 moushaokun Exp $
 */
public class DefaultTest extends Junit5Launcher {

    @Override
    public void activateTestCase(ThemisTestCaseProvider themisTestCaseProvider) {
        themisTestCaseProvider.activateTestCaseIndexes("MyService_pay_data_case_1");
    }
}

