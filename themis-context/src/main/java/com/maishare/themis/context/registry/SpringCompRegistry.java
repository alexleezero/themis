/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context.registry;

import lombok.Getter;
import org.springframework.context.ApplicationContext;

/**
 * Spring 组件注册中心 实现
 * @author moushaokun
 * @version @Id: SpringCompRegistry.java, v 0.1 2020年03月11日 11:11 moushaokun Exp $
 */
public class SpringCompRegistry extends DefaultCompRegistry {

    @Getter
    private ApplicationContext applicationContext;

    public SpringCompRegistry(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
    
}

