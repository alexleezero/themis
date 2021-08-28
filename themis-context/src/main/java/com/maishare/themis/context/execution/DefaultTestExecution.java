/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.execution;

import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.domain.LibraData;
import com.maishare.themis.context.domain.LibraFile;

import lombok.Getter;
import lombok.Setter;

/**
 * 默认的测试用例运行逻辑类
 * @author lijian
 * @version @Id: DefaultTestExecution.java, v 0.1 2020年03月02日 14:34 lijian Exp $
 */
public class DefaultTestExecution implements ThemisTestExecution {

    @Getter
    @Setter
    private LibraFile     libraFile;

    @Getter
    @Setter
    private LibraData     libraData;

    @Getter
    @Setter
    private ThemisContext themisContext;
}
