/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.execute;

import com.maishare.themis.common.enums.LibraCompType;
import com.maishare.themis.component.base.func.BaseLibraComponent;

/**
 * Execute组件模板
 * @author moushaokun
 * @version @Id: ExecuteComponent.java, v 0.1 2020年03月11日 13:59 moushaokun Exp $
 */
public abstract class ExecuteComponent<T> extends BaseLibraComponent<T> {

    @Override
    public LibraCompType type() {
        return LibraCompType.EXECUTE;
    }
}

