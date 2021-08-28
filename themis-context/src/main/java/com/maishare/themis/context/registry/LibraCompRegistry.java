/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.registry;

import com.maishare.themis.common.enums.LibraCompType;
import com.maishare.themis.context.ThemisContext;
import com.maishare.themis.context.component.LibraComponent;

/**
 * libra组件注册中心
 * @author moushaokun
 * @version @Id: LibraCompRegistry.java, v 0.1 2020年03月11日 11:02 moushaokun Exp $
 */
public interface LibraCompRegistry {

    /**
     * 注册组件
     */
    void registry(ThemisContext themisContext);

    /**
     * 获取组件
     */
    LibraComponent retrieve(LibraCompType compType, String compName);
}

