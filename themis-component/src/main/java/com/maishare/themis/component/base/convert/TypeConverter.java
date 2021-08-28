/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.convert;

import com.maishare.themis.extension.SPI;

/**类型转换器接口定义
 * @author hejianbing
 * @version @Id: TypeConverter.java, v 0.1 2020年03月20日 14:42 hejianbing Exp $
 */
@SPI
public interface TypeConverter<S,T> extends org.springframework.core.convert.converter.Converter<S,T>{
}