/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.processor;

import com.maishare.themis.common.exception.DBOperationException;
import com.maishare.themis.component.base.db.execoter.SqlExecutor;
import com.maishare.themis.component.base.db.operation.DBInsertOperation;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.component.base.db.operation.OperationType;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 插入操作处理器
 * @author moushaokun
 * @version @Id: DBInsertOperationProcessor.java, v 0.1 2020年03月20日 15:56 moushaokun Exp $
 */
public class DBInsertOperationProcessor extends AbstractDBOperationProcessor<DBInsertOperation, Integer>{

    @Override
    public boolean support(OperationType operationType) {
        return DBOperationType.INSERT == operationType;
    }

    @Override
    public Integer process(DBInsertOperation operation) {
        if (MapUtils.isEmpty(operation.getSets())){
            throw new DBOperationException("新增操作的设值不能为空");
        }

        StringBuilder sb = new StringBuilder("insert into ");
        List<Object> params = new ArrayList<>();

        sb.append(operation.getTableName());
        sb.append(" ( ");

        StringBuilder paramStr = new StringBuilder();
        StringBuilder columnStr = new StringBuilder();
        operation.getSets().forEach((k, v) -> {
            columnStr.append(k);
            columnStr.append(",");
            params.add(v);
            paramStr.append("?,");
        });

        sb.append(columnStr.substring(0, columnStr.length() -1));
        sb.append(" ) values (");
        sb.append(paramStr.subSequence(0, paramStr.length() - 1));
        sb.append(" ) ");

        return SqlExecutor.getInstance(
                operation.getThemisTestExecution().getThemisContext().getThemisConfig().getDataSource())
                .update(sb.toString(), params.toArray());
    }
}

