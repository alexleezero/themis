/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.utils;

import java.util.Map;

import com.maishare.themis.common.exception.FileParseException;

/**
 * 数据检查工具类
 * @author moushaokun
 * @version @Id: DataCheckUtils.java, v 0.1 2020年03月20日 15:07 moushaokun Exp $
 */
public class DataCheckUtils {

    public static void checkKeyInMap(Map<String,Object> dataMap, String key) {
        if (dataMap.get(key) == null) {
            throw new FileParseException(String.format("数据文件缺少关键数据项[%s]", key));
        }
    }
}

