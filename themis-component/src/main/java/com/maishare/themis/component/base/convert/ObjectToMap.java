/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.convert;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Object==>>Map 转换器
 * @author hejianbing
 * @version @Id: ObjectToMap.java, v 0.1 2020年03月27日 13:38 hejianbing Exp $
 */
public class ObjectToMap implements Converter<Object, Map<String, Object>> {
    @Override
    public Map convert(Object source) {
        Map<String, Object> data = new HashMap<>(256);
        
        BeanMap beanMap = BeanMap.create(source);
        
        for (Object key : beanMap.keySet()) {
            data.put(key + "", beanMap.get(key));
        }
        return data;
    }
}