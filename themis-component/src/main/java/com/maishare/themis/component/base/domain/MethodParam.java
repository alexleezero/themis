/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.domain;

import com.maishare.themis.component.enums.MockParamType;
import com.maishare.themis.context.execution.ThemisTestExecution;
import lombok.Getter;
import lombok.Setter;

/**
 * 方法参数
 * @author hejianbing
 * @version @Id: MethodParam.java, v 0.1 2020年03月28日 21:00 hejianbing Exp $
 */
@Setter
@Getter
public class MethodParam {

    private ThemisTestExecution themisTestExecution;

    private Object              bean;

    private String              methodName;

    private Object              data;

    private MockParamType       mockParamType = MockParamType.PARAM;

}