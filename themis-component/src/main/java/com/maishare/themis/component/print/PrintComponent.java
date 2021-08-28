/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.print;

import com.maishare.themis.common.enums.LibraCompType;
import com.maishare.themis.component.base.func.BaseLibraComponent;

/**
 * Print 组件
 * @author moushaokun
 * @version @Id: PrintComponent.java, v 0.1 2020年03月11日 14:13 moushaokun Exp $
 */
public abstract class PrintComponent<T> extends BaseLibraComponent<T> {

    @Override
    public LibraCompType type() {
        return LibraCompType.PRINT;
    }
}

