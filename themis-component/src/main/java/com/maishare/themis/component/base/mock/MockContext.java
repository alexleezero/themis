/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.mock;

import com.maishare.themis.component.base.data.PrepareMockData;
import com.maishare.themis.context.execution.ThemisTestExecution;
import lombok.Getter;
import lombok.Setter;

/**
 * mock 处理参数上下文信息
 * @author hejianbing
 * @version @Id: MockContext.java, v 0.1 2020年03月25日 13:57 hejianbing Exp $
 */
@Setter
@Getter
public class MockContext {

    private ThemisTestExecution themisTestExecution;

    private MethodInvocation    methodInvocation;

    private PrepareMockData     prepareMockData;

    /** 宿主对象 */
    private Object              hostBean;
    /** mock对象 */
    private Object              targetObject;

    /** mock 后对象*/
    private Object              proxyObject;

}