/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 日期类型转换器
 * @author hejianbing
 * @version @Id: StringDateConverter.java, v 0.1 2020年03月20日 14:35 hejianbing Exp $
 */
public class StringDateConverter implements Converter<String, Date> {
	
	private static final List<String> patternList = Arrays.asList(
		"yyyy-MM-dd HH:mm:ss:SSS",
		"yyyy-MM-dd HH:mm:ss",
		"yyyy-MM-dd");
	
	
    @Override
    public Date convert(String date) {
        Date r = null;
        for (String pattern : patternList) {
            SimpleDateFormat sd = new SimpleDateFormat(pattern);
            try {
                r = sd.parse(date);
            } catch (Exception e) {
            }
        }
        if (r == null) {
            throw new IllegalAccessError("日期转换出错！！");
        }
        return r;
    }
}