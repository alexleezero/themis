/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.processor;

import com.maishare.themis.common.exception.DBOperationException;
import com.maishare.themis.component.base.db.execoter.SqlExecutor;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.component.base.db.operation.DBSelectOperation;
import com.maishare.themis.component.base.db.operation.OperationType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 查询操作处理器
 * @author moushaokun
 * @version @Id: DBSelectOperationProcessor.java, v 0.1 2020年03月20日 15:56 moushaokun Exp $
 */
public class DBSelectOperationProcessor extends AbstractDBOperationProcessor<DBSelectOperation, List<Map<String, Object>>>{

    @Override
    public boolean support(OperationType operationType) {
        return DBOperationType.SELECT == operationType;
    }

    @Override
    public List<Map<String, Object>> process(DBSelectOperation operation) {
        if (MapUtils.isEmpty(operation.getConditions())){
            throw new DBOperationException("查询操作的条件不能为空");
        }

        StringBuilder sb = new StringBuilder("select ");

        StringBuilder selectFields = new StringBuilder();
        Set<String> allColumns = new HashSet<>();
        if (CollectionUtils.isNotEmpty(operation.getComparisons())){
            operation.getComparisons().forEach(map -> map.forEach((k, v) -> allColumns.add(k)));
        }
        allColumns.forEach(k -> {
            selectFields.append(k);
            selectFields.append(",");
        });

        if (selectFields.length() > 0) {
            sb.append(selectFields.substring(0, selectFields.length() - 1));
        } else {
            sb.append(" * ");
        }

        sb.append(" from ");
        sb.append(operation.getTableName());
        sb.append(" where ");

        Pair<String, List<Object>> conditionStrAndParam = getConditionStrAndParam(operation.getConditions());
        sb.append(conditionStrAndParam.getLeft());

        return SqlExecutor.getInstance(
                operation.getThemisTestExecution().getThemisContext().getThemisConfig().getDataSource())
                .queryForListMap(sb.toString(), conditionStrAndParam.getRight().toArray());
    }
}

