/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.param;

import com.maishare.themis.context.component.LibraComponentParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 通用组件调用参数-索引参数
 * @author moushaokun
 * @version @Id: CommonIndexCompParam.java, v 0.1 2020年03月13日 10:46 moushaokun Exp $
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonIndexCompParam implements LibraComponentParam {

    /** 文件名 */
    private String fileName;

    /** 索引 */
    private String index;
}

