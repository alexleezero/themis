/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.processor;

import com.maishare.themis.common.exception.DBOperationException;
import com.maishare.themis.component.base.db.execoter.SqlExecutor;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.component.base.db.operation.DBUpdateOperation;
import com.maishare.themis.component.base.db.operation.OperationType;
import org.apache.commons.collections.MapUtils;
import org.bravo.gaia.commons.domain.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 更新操作处理器
 * @author moushaokun
 * @version @Id: DBUpdateOperationProcessor.java, v 0.1 2020年03月20日 15:56 moushaokun Exp $
 */
public class DBUpdateOperationProcessor extends AbstractDBOperationProcessor<DBUpdateOperation, Integer>{

    @Override
    public boolean support(OperationType operationType) {
        return DBOperationType.UPDATE == operationType;
    }

    @Override
    public Integer process(DBUpdateOperation operation) {
        if (MapUtils.isEmpty(operation.getConditions())){
            throw new DBOperationException("修改操作的条件不能为空");
        }

        if (MapUtils.isEmpty(operation.getSets())){
            throw new DBOperationException("修改操作的设值不能为空");
        }

        Pair<String, List<Object>> conditionStrAndParam = getConditionStrAndParam(operation.getConditions());

        StringBuilder sb = new StringBuilder("update ");
        sb.append(operation.getTableName());

        StringBuilder sets = new StringBuilder(" set ");
        List<Object> setParams = new ArrayList<>();
        operation.getSets().forEach((k, v) -> {
            sets.append(k);
            sets.append("=? ,");
            setParams.add(v);
        });

        sb.append(sets.substring(0, sets.length() - 1));
        sb.append(" where ");
        sb.append(conditionStrAndParam.getFirst());

        setParams.addAll(conditionStrAndParam.getSecond());

        return SqlExecutor.getInstance(
                operation.getThemisTestExecution().getThemisContext().getThemisConfig().getDataSource())
                .update(sb.toString(), setParams.toArray());
    }

}

