/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.domain;

import com.maishare.themis.context.execution.ThemisTestExecution;

/**
 * libra逻辑执行实例接口
 * @author moushaokun
 * @version @Id: LibraInstance.java, v 0.1 2020年03月10日 16:19 moushaokun Exp $
 */
public interface LibraInstance{

    /** 获取索引 */
    String getIndex();

    /** 获取测试上下文 */
    ThemisTestExecution getThemisTestExecution();

    /** 运行实例 */
    void run();
}

