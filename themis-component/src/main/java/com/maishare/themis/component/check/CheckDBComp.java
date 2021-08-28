/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.maishare.themis.common.constants.LibraCompNameConstants;
import com.maishare.themis.component.base.db.builder.DBOperationBuilder;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.component.base.db.operation.DBSelectOperation;
import com.maishare.themis.component.base.db.operation.Operation;
import com.maishare.themis.component.base.domain.CompareItem;
import com.maishare.themis.component.base.func.OperationalComp;
import com.maishare.themis.component.base.func.TypeFileFilter;
import com.maishare.themis.component.base.support.DefaultOperationalDBComp;
import com.maishare.themis.component.base.support.DefaultTypeFileFilter;
import com.maishare.themis.component.param.CommonIndexCompParam;
import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.context.execution.ThemisTestExecution;

/**
 * Check db 组件，检查db中的数据
 * @author moushaokun
 * @version @Id: CheckDBComp.java, v 0.1 2020年03月11日 14:09 moushaokun Exp $
 */
public class CheckDBComp extends CheckComponent<CommonIndexCompParam> {

    private static final Logger          LOGGER          = LoggerFactory
        .getLogger(CheckDBComp.class);
    private static final TypeFileFilter  typeFileFilter  = new DefaultTypeFileFilter();
    private static final OperationalComp operationalComp = new DefaultOperationalDBComp();

    @Override
    public void act(ThemisTestExecution themisTestExecution, CommonIndexCompParam commonIndexCompParam) {
        LOGGER.debug("检查数据，参数: " + commonIndexCompParam.toString());

        TypeFile typeFile = typeFileFilter.doFilter(themisTestExecution.getLibraFile().getLibraCheckFiles(),
                commonIndexCompParam.getFileName());

        List<Operation> operations = operationalComp.operations(DBOperationBuilder.class,
            themisTestExecution, typeFile, commonIndexCompParam.getIndex());

        operationalComp.checkSupportType("检查数据", operations, DBOperationType.SELECT);

        operations.forEach(operation -> {
            DBSelectOperation selectOperation = (DBSelectOperation)operation;

            //获取比较的key
            List<String> keys = selectOperation.getKeys();

            //获取比较的值
            List<Map<String, CompareItem>> comparisons = selectOperation.getComparisons();

            //获取执行结果
            List<Map<String, Object>> executeResultList = selectOperation.execute();

            //比较记录行数
            if (selectOperation.getCheckRows() != null) {
                Assertions.assertEquals(selectOperation.getCheckRows().intValue(), executeResultList.size());
            }

            //比较具体值
            if (CollectionUtils.isNotEmpty(comparisons)) {
                compareResult(comparisons, executeResultList, keys);
            }
        });
    }

    private void compareResult(List<Map<String, CompareItem>> comparisons, List<Map<String, Object>> executeResultList, List<String> keys) {
        //检查逻辑: 比较数据的条数相同
        Assertions.assertEquals(comparisons.size(), executeResultList.size(),"查询数据行数与期望数据项行数不一致");

        //检查逻辑：比较数据内容相同
        if (CollectionUtils.isNotEmpty(keys)) {
            //存在比较关键字时，找到与比较关键字相同的数据分别进行比较，只要有一个字段不同则检查失败
            for (Map<String, CompareItem> compareItemMap : comparisons) {
                Map<String, Object> executeResult = findItem(executeResultList, compareItemMap, keys);
                //关键值不匹配
                Assertions.assertNotNull(executeResult,
                        String.format("查询数据与期望数据不匹配：关键值不匹配，期望关键值[%s]，查询关键值[%s]",
                                JSON.toJSONString(getKeyMap(compareItemMap, keys)),
                                JSON.toJSONString(getKeyMap(executeResultList, keys))));

                //逐个比较字段，有一个字段不同则不匹配
                for (Map.Entry<String, CompareItem> entry : compareItemMap.entrySet()) {
                    Assertions.assertTrue(
                            entry.getValue().getOperator()
                                    .compare(executeResult.get(entry.getKey()), entry.getValue().getValue()),
                            String.format("查询数据与期望数据不匹配，位于字段[%s]，期望值[%s]，查询值[%s]，比较符[%s]",
                                    entry.getKey(), entry.getValue().getValue(), executeResult.get(entry.getKey()), entry.getValue().getOperator().getCode()));
                }
            }
        } else {
            //不存在比较关键字时，逐条以此比较，只要整条数据分别相同，则检查成功
            for (Map<String, CompareItem> compareItemMap : comparisons) {
                boolean equal = false;

                //只要真实数据有一条与检查数据匹配，那么这条数据就是匹配的
                for (Map<String, Object> resultItem : executeResultList) {
                    equal = compare(compareItemMap, resultItem);

                    if (equal) {
                        break;
                    }
                }

                //只要检查数据中有一条不能与真实数据匹配，那么整个数据都不匹配
                Assertions.assertTrue(equal,
                        String.format("查询数据中没有与期望数据项匹配的数据：期望数据[%s]，查询数据[%s]",
                                JSON.toJSONString(compareItemMap),
                                JSON.toJSONString(executeResultList)));
            }
        }
    }

    private Map<String, Object> getKeyMap(Map<String, CompareItem> compareItemMap, List<String> keys) {
        Map<String, Object> map = new HashMap<>(4);
        keys.forEach(key -> map.put(key, compareItemMap.get(key).getValue()));
        return map;
    }

    private Map<String, List<Object>> getKeyMap(List<Map<String, Object>> executeResultList, List<String> keys) {
        Map<String, List<Object>> map = new HashMap<>(4);
        keys.forEach(key -> {
            List<Object> list = new ArrayList<>(4);
            executeResultList.forEach(resultMap -> list.add(resultMap.get(key)));
            map.put(key, list);
        });
        return map;
    }

    private boolean compare(Map<String, CompareItem> compareItemMap, Map<String, Object> resultItem) {
        boolean flag = true;

        for (Map.Entry<String, CompareItem> entry : compareItemMap.entrySet()) {
            flag = entry.getValue().getOperator().compare(resultItem.get(entry.getKey()), entry.getValue().getValue());
            if (!flag) {
                break;
            }
        }

        return flag;
    }

    private Map<String, Object> findItem(List<Map<String, Object>> executeResultList,
                                        Map<String, CompareItem> compareItemMap,
                                        List<String> keys) {
        if(executeResultList == null || compareItemMap == null) {
            return null;
        }

        return executeResultList.stream().filter(executeResultMap -> {
            boolean valid = true;
            for (String key : keys){
                valid = compareItemMap.get(key).getOperator().compare(executeResultMap.get(key), compareItemMap.get(key).getValue());
                if(!valid){
                    break;
                }
            }
            return valid;
        }).findAny().orElse(null);
    }


    @Override
    public String name() {
        return LibraCompNameConstants.DB;
    }

}

