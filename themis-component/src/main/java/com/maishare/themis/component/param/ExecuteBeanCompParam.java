/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.param;

import com.maishare.themis.context.component.LibraComponentParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 执行组件调用参数
 * @author moushaokun
 * @version @Id: ExecuteBeanCompParam.java, v 0.1 2020年03月13日 10:52 moushaokun Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteBeanCompParam implements LibraComponentParam {

    /** spring bean 名称 */
    private String bean;

    /** 调用方法 */
    private String method;
}

