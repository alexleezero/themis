/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.convert;

import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.extension.ExtensionLoader;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.util.Set;

/**
 * 类型转换执行器
 * @author hejianbing
 * @version @Id: TypeConverterExecutor.java, v 0.1 2020年03月20日 14:45 hejianbing Exp $
 */
public class TypeConverterExecutor {

    private static DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
	
	private static final Set<TypeConverter> extensionConvertList = ExtensionLoader
		.getExtensionLoader(TypeConverter.class).getSupportedExtensionInstances();
	
	
	private TypeConverterExecutor() {
        extensionConvertList.stream().forEach(convert -> {
            conversionService.addConverter(convert);
        });
        
        conversionService.addConverter(new ObjectToMap());
        conversionService.addConverter(new StringDateConverter());
    }

    public <T> T convert(Object source, Class<T> targetType) {
        if (source == null || targetType == null) {
            throw new ComponentException("类型转换源和目标类型不能为空");
        }
        return conversionService.convert(source, targetType);
    }

    public static TypeConverterExecutor getInstance() {
        return TypeConverterHolder.INSTANCE;
    }

    private static class TypeConverterHolder {

        public static final TypeConverterExecutor INSTANCE = new TypeConverterExecutor();
    }
}