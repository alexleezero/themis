/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.processor;

import com.maishare.themis.common.exception.DBOperationException;
import com.maishare.themis.component.base.db.execoter.SqlExecutor;
import com.maishare.themis.component.base.db.operation.DBDeleteOperation;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.component.base.db.operation.OperationType;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * 删除操作处理器
 * @author moushaokun
 * @version @Id: DBDeleteOperationProcessor.java, v 0.1 2020年03月20日 15:56 moushaokun Exp $
 */
public class DBDeleteOperationProcessor extends AbstractDBOperationProcessor<DBDeleteOperation, Integer>{

    @Override
    public boolean support(OperationType operationType) {
        return DBOperationType.DELETE == operationType;
    }
    
    @Override
    public Integer process(DBDeleteOperation operation) {
        if (MapUtils.isEmpty(operation.getConditions())){
            throw new DBOperationException("删除操作的条件不能为空");
        }

        Pair<String, List<Object>> conditionStrAndParam = getConditionStrAndParam(operation.getConditions());

        StringBuilder sb = new StringBuilder("delete from ");
        sb.append(operation.getTableName());
        sb.append(" where ");
        sb.append(conditionStrAndParam.getLeft());

        return SqlExecutor.getInstance(
                operation.getThemisTestExecution().getThemisContext().getThemisConfig().getDataSource())
                .update(sb.toString(), conditionStrAndParam.getRight().toArray());
    }

}

