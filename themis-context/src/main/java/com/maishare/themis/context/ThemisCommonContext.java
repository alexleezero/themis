/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.context;

import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.config.ThemisConfig;
import com.maishare.themis.context.registry.DefaultCompRegistry;
import com.maishare.themis.context.registry.LibraCompRegistry;
import com.maishare.themis.context.testcase.ThemisTestCase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * themis通用上下文实现
 * @author lijian
 * @version @Id: ThemisCommonContext.java, v 0.1 2020年03月02日 11:49 lijian Exp $
 */
public class ThemisCommonContext implements ThemisContext {

    private Map<String, ThemisTestCase> themisTestCases;

    @Getter
    private ThemisConfig         themisConfig;

    @Getter
    @Setter
    private LibraCompRegistry    libraCompRegistry;

    public ThemisCommonContext(ThemisConfig themisConfig) {
        this.themisConfig = themisConfig;
    }

    @Override
    public void init() {
        this.libraCompRegistry = createLibraCompRegistry();
        this.libraCompRegistry.registry(this);
    }

    @Override
    public void populateTestCases(Map<String, ThemisTestCase> themisTestCases) {
        this.themisTestCases = themisTestCases;
    }

    @Override
    public Map<String, ThemisTestCase> getTestCases() {
        return this.themisTestCases;
    }
    
    @Override
    public <T> T getBean(String beanName) {
        try{
            Class<?> aClass = Class.forName(beanName);
            return (T)BeanUtils.instantiateClass(aClass);
        }catch(Exception e){
            throw new ComponentException(String.format("容器中未找到bean对象%s", beanName));
        }
    }
    
    protected LibraCompRegistry createLibraCompRegistry(){
        return new DefaultCompRegistry();
    }

}
