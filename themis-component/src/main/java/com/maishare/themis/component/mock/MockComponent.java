/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.mock;

import com.maishare.themis.common.enums.LibraCompType;
import com.maishare.themis.component.base.func.BaseLibraComponent;
import com.maishare.themis.context.component.LibraComponent;

/**
 * Mock组件模板
 * @author moushaokun
 * @version @Id: MockComponent.java, v 0.1 2020年03月11日 13:57 moushaokun Exp $
 */
public abstract class MockComponent<T> extends BaseLibraComponent<T> {

    @Override
    public LibraCompType type() {
        return LibraCompType.MOCK;
    }
}

