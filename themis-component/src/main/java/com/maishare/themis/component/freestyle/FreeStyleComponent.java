/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.freestyle;

import com.maishare.themis.common.enums.LibraCompType;
import com.maishare.themis.component.base.func.BaseLibraComponent;

/**
 * FreeStyle 组件模板
 * @author moushaokun
 * @version @Id: FreeStyleComponent.java, v 0.1 2020年03月11日 14:15 moushaokun Exp $
 */
public abstract class FreeStyleComponent<T> extends BaseLibraComponent<T> {

    @Override
    public LibraCompType type() {
        return LibraCompType.FREESTYLE;
    }
}

