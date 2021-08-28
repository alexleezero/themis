/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.clear;

import com.maishare.themis.common.enums.LibraCompType;
import com.maishare.themis.component.base.func.BaseLibraComponent;

/**
 * Clear 组件模板
 * @author moushaokun
 * @version @Id: ClearComponent.java, v 0.1 2020年03月11日 14:11 moushaokun Exp $
 */
public abstract class ClearComponent<T> extends BaseLibraComponent<T> {

    @Override
    public LibraCompType type() {
        return LibraCompType.CLEAR;
    }
}

