/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.mock;

import com.maishare.themis.component.enums.MockParamType;
import lombok.Getter;
import lombok.Setter;

/**
 * mock 方法定义
 * @author hejianbing
 * @version @Id: MockMethod.java, v 0.1 2020年03月25日 16:27 hejianbing Exp $
 */
@Setter
@Getter
public class MockMethod {

    /** 代理对像 **/
    private Object           proxy;

    /** 实际bean宿主对象 **/
    private Object           hostBean;

    /** 实际bean实例对象 **/
    private Object           targetBean;

    private Object           result;

    private MockParamType    paramType;
    /** 方法信息 */
    private MethodInvocation methodInvocation;

}