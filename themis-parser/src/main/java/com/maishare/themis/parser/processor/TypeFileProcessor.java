/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.processor;

import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.extension.SPI;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 资源文件解析执行器
 * @author moushaokun
 * @version @Id: TypeFileProcessor.java, v 0.1 2020年03月10日 15:53 moushaokun Exp $
 */
@SPI
public interface TypeFileProcessor<T> {
    
    Map<String,Object> dataCache = new ConcurrentHashMap<>();
    
    /**
     * 文件处理
     */
    default T process(TypeFile typeFile) {
        String key = typeFile.getFile().getPath();
    
        if (null != dataCache.get(key)) {
            return (T) dataCache.get(key);
        }
    
        T data = doProcess(typeFile);
    
        dataCache.put(key, data);
    
        return data;
    }
    
    T doProcess(TypeFile typeFile);
    
}

