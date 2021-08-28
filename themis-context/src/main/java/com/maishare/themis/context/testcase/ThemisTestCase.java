/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.testcase;

/**
 * themis测试用例接口
 * 一个themis测试用例接口 = 一个libra data执行用例
 * @author lijian
 * @version @Id: ThemisTestCase.java, v 0.1 2020年01月17日 13:45 lijian Exp $
 */
public interface ThemisTestCase {

    /**
     * 测试用例名称
     */
    String displayName();

    /**
     * 执行测试用例
     */
    void execute();

}
