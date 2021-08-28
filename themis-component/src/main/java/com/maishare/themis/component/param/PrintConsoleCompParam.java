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
 * 打印组件参数
 * @author moushaokun
 * @version @Id: PrintConsoleCompParam.java, v 0.1 2020年03月17日 10:14 moushaokun Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrintConsoleCompParam implements LibraComponentParam {

    private String content;
}

