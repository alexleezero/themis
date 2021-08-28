/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.prepare;

import com.maishare.themis.common.enums.LibraCompType;
import com.maishare.themis.component.base.func.BaseLibraComponent;
import com.maishare.themis.context.component.LibraComponent;

/**
 * Prepare组件模板
 * @author moushaokun
 * @version @Id: PrepareComponent.java, v 0.1 2020年03月11日 11:08 moushaokun Exp $
 */
public abstract class PrepareComponent<T> extends BaseLibraComponent<T> {

    @Override
    public LibraCompType type() {
        return LibraCompType.PREPARE;
    }
    
   
    
}

