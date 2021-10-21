/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.factory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.maishare.themis.common.constants.LibraCompDBDataKeys;
import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.component.base.domain.CompareItem;
import com.maishare.themis.component.base.domain.CompareOperator;
import com.maishare.themis.component.utils.ExpressionExecutor;
import com.maishare.themis.context.execution.ThemisTestExecution;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DB操作工厂Yaml模板
 * @author moushaokun
 * @version @Id: AbstractYamlDBOperationFactory.java, v 0.1 2020年03月20日 15:25 moushaokun Exp $
 */
public abstract class AbstractYamlDBOperationFactory implements DBOperationFactory {

    @Override
    public boolean support(DBOperationType operationType, FileType type) {
        return support(operationType) && FileType.YAML == type;
    }

    public abstract boolean support(DBOperationType operationType);

    public Object getValue(ThemisTestExecution themisTestExecution, Map<String, Object> data, String varName){
        Object value = data.get(varName);

        if (value instanceof String && value.toString().startsWith(LibraCompDBDataKeys.VAR_PREFIX)) {
            value = getProp(themisTestExecution, value.toString().substring(1));

        } else if (value instanceof List){
            value = resetList(themisTestExecution, (List<Object>) value);

        } else if (value instanceof Map) {
            value = resetMap(themisTestExecution, (Map<String, Object>) value);

        }

        return value;
    }

    public Object resetMap(ThemisTestExecution themisTestExecution, Map<String, Object> value) {
        Map<String, Object> newMap = new HashMap<>();

        value.forEach((k,v) -> {
            if (v instanceof Map) {
                newMap.put(k, resetMap(themisTestExecution, (Map<String, Object>) v));
            } else if(v instanceof List){
                newMap.put(k, resetList(themisTestExecution, (List<Object>) v));
            } else if (v != null && v.toString().startsWith(LibraCompDBDataKeys.VAR_PREFIX)) {
                newMap.put(k, getProp(themisTestExecution, v.toString().substring(1)));
            } else {
                newMap.put(k, v);
            }
        });

        return newMap;
    }

    public Object resetList(ThemisTestExecution themisTestExecution, List<Object> value) {
        List<Object> newList = new ArrayList<>();

        value.forEach(v -> {
            if (v instanceof Map) {
                newList.add(resetMap(themisTestExecution, (Map<String, Object>) v));
            } else if(v instanceof List){
                newList.add(resetList(themisTestExecution, (List<Object>) v));
            } else if (v != null && v.toString().startsWith(LibraCompDBDataKeys.VAR_PREFIX)) {
                newList.add(getProp(themisTestExecution, v.toString().substring(1)));
            } else {
                newList.add(v);
            }
        });

        return newList;
    }

    public Object getProp(ThemisTestExecution themisTestExecution, String propName) {
        return ExpressionExecutor.execute(propName, themisTestExecution.getLibraData().getData());
    }

    public Map<String, CompareItem> toCompareItemMap(Object value) {
        return toCompareItemMap((Map<String, Object>)value, new HashSet<>());
    }

    public List<String> toListString(Object value) {

        return (List<String>) value;
    }

    public Pair<List<String>/*keys*/, List<Map<String, CompareItem>>/*comparisons*/> toCompareItemListMap(Object value) {
        if(value instanceof Map){
            value = Lists.newArrayList(value);
        }
        Set<String> keys = Sets.newHashSet();

        List<Map<String, CompareItem>> compareListMap = ((List<Map<String, Object>>) value).stream()
                .map(map -> toCompareItemMap(map, keys))
                .collect(Collectors.toList());

        return Pair.of(Arrays.asList(keys.toArray(new String[]{})), compareListMap);
    }

    public Map<String, Object> toMap(Object value) {
        return (Map<String, Object>) value;
    }

    public Map<String, CompareItem> toCompareItemMap(Map<String, Object> map, Set<String> keys) {
        Map<String, CompareItem> compareItemMap = new HashMap<>(16);

        map.forEach((k,v) -> {
            Map<String, Object> valueMap = (Map<String, Object>) v;

            compareItemMap.put(k,
                    new CompareItem(
                            CompareOperator.get(valueMap.get(LibraCompDBDataKeys.OPERATION).toString()),
                            valueMap.get(LibraCompDBDataKeys.VALUE)));
            if(valueMap.get(LibraCompDBDataKeys.IS_KEY) != null
                    && Boolean.parseBoolean(valueMap.get(LibraCompDBDataKeys.IS_KEY).toString())) {
                keys.add(k);
            }
        });

        return compareItemMap;
    }
}

