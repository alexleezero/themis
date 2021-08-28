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
 * 关联组件参数
 * @author moushaokun
 * @version @Id: ReferenceCompParam.java, v 0.1 2020年03月13日 19:12 moushaokun Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceCompParam implements LibraComponentParam {

    private String dataIndex;
}

