/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.check;

import com.maishare.themis.common.enums.LibraCompType;
import com.maishare.themis.component.base.func.BaseLibraComponent;

/**
 * Check 组件模板
 * @author moushaokun
 * @version @Id: CheckComponent.java, v 0.1 2020年03月11日 14:02 moushaokun Exp $
 */
public abstract class CheckComponent<T> extends BaseLibraComponent<T> {

    @Override
    public LibraCompType type() {
        return LibraCompType.CHECK;
    }
}

