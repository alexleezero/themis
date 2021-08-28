/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 比较项
 * @author moushaokun
 * @version @Id: CompareItem.java, v 0.1 2020年03月19日 12:20 moushaokun Exp $
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompareItem {

    /** 比较符 */
    private CompareOperator operator;

    /** 值 */
    private Object          value;

}
