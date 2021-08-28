/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context;

import com.maishare.themis.common.exception.ComponentException;
import org.springframework.context.ApplicationContext;

import com.maishare.themis.config.ThemisConfig;
import com.maishare.themis.context.registry.LibraCompRegistry;
import com.maishare.themis.context.registry.SpringCompRegistry;

import lombok.Getter;

/**
 * themis spring上下文
 * @author lijian
 * @version @Id: ThemisSpringContext.java, v 0.1 2020年03月02日 13:11 lijian Exp $
 */
public class ThemisSpringContext extends ThemisCommonContext {

    @Getter
    private ApplicationContext applicationContext;

    public ThemisSpringContext(ApplicationContext applicationContext,
                               ThemisConfig themisConfig) {
        super(themisConfig);
        this.applicationContext = applicationContext;
    }

    @Override
    protected LibraCompRegistry createLibraCompRegistry() {
        return new SpringCompRegistry(this.applicationContext);
    }
    
    
    @Override
    public <T> T  getBean(String beanName) {
        try {
            Class<?> aClass = Class.forName(beanName);
            
            return (T)this.applicationContext.getBean(aClass);
        } catch (ClassNotFoundException e) {
            throw new ComponentException(String.format("未找到实例化对象%s", beanName));
        }
       
    }
}
