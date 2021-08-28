/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.parser.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.common.exception.FileParseException;
import com.maishare.themis.context.domain.LibraData;
import com.maishare.themis.context.domain.LibraFile;
import com.maishare.themis.context.domain.LibraInstance;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.parser.domain.DefaultLibraInstance;

/**
 * yaml格式的data文件对应的libra实例生成器
 * @author moushaokun
 * @version @Id: YamlLibraInstanceGenerator.java, v 0.1 2020年03月17日 15:46 moushaokun Exp $
 */
public class YamlLibraInstanceGenerator extends AbstractLibraInstanceGenerator {

    @Override
    public LibraInstance assembleInstance(ThemisTestExecution execution, LibraData libraData) {
        return new DefaultLibraInstance(execution, libraData.getIndex());
    }

    @Override
    public List<LibraData> assembleLibraData(LibraFile libraFile) {

        if (libraFile.getLibraDataFile().getType() != FileType.YAML) {
            throw new FileParseException("数据文件类型不支持，支持的类型为[yaml]");
        }

        List<Map<String,Object>> dataCases = libraFile.getLibraDataFile().getOriginal();

        List<LibraData> libraDataList = new ArrayList<>();
        dataCases.forEach(dataCase -> {
            checkData(dataCase, "data_case");

            Map<String,Object> dataMap = toMap(dataCase.get("data_case"));

            LibraData libraData = new LibraData();

            checkData(dataMap, "index");

            checkData(dataMap, "data");

            libraData.setIndex(toString(dataMap.get("index")));
            libraData.setDesc(toString(dataMap.get("desc")));
            libraData.setAuthor(toString(dataMap.get("author")));
            libraData.setData(toMap(dataMap.get("data")));

            libraDataList.add(libraData);
        });

        return libraDataList;
    }

    @Override
    public boolean matches(LibraFile libraFile) {
        return libraFile.getLibraDataFile().getType() == FileType.YAML;
    }


    private static String toString(Object obj) {
        return obj == null ? null : obj.toString();
    }


    private static Map<String, Object> toMap(Object obj) {
        if (obj instanceof Map) return (Map<String, Object>) obj;

        return null;
    }

    private static void checkData(Map<String,Object> dataMap, String key) {
        if (dataMap.get(key) == null) {
            throw new FileParseException(String.format("数据文件缺少关键数据项[%s]", key));
        }
    }
}

