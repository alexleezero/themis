/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.factory;

import java.util.Map;

import com.maishare.themis.common.constants.LibraCompDBDataKeys;
import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.common.exception.DBOperationException;
import com.maishare.themis.component.base.db.operation.DBOperation;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.component.base.db.operation.DBUpdateOperation;
import com.maishare.themis.component.utils.DataCheckUtils;
import com.maishare.themis.context.execution.ThemisTestExecution;

/**
 * 更新操作工厂
 * @author moushaokun
 * @version @Id: DBUpdateOperationFactory.java, v 0.1 2020年03月20日 14:58 moushaokun Exp $
 */
public class DBUpdateOperationFactory extends AbstractYamlDBOperationFactory{

    @Override
    public boolean support(DBOperationType operationType) {
        return DBOperationType.UPDATE == operationType;
    }

    @Override
    public DBOperation create(ThemisTestExecution themisTestExecution, Map<String, Object> data, DBOperationType operationType, FileType type) {
        DBUpdateOperation operation = new DBUpdateOperation(themisTestExecution);

        DataCheckUtils.checkKeyInMap(data, LibraCompDBDataKeys.SETS);
        DataCheckUtils.checkKeyInMap(data, LibraCompDBDataKeys.CONDITIONS);


        operation.setTableName(getValue(themisTestExecution, data, LibraCompDBDataKeys.TABLE_NAME).toString());
        operation.setCheckRows(getValue(themisTestExecution, data, LibraCompDBDataKeys.CHECK_ROWS) == null ? null
                : Integer.valueOf(getValue(themisTestExecution, data, LibraCompDBDataKeys.CHECK_ROWS).toString()));

        try{
            operation.setSets(toMap(getValue(themisTestExecution, data, LibraCompDBDataKeys.SETS)));
        } catch (Exception e) {
            throw new DBOperationException(String.format("数据项[%s]格式不正确", LibraCompDBDataKeys.SETS), e);
        }

        try{
            operation.setConditions(toCompareItemMap(getValue(themisTestExecution, data, LibraCompDBDataKeys.CONDITIONS)));
        } catch (Exception e) {
            throw new DBOperationException(String.format("数据项[%s]格式不正确", LibraCompDBDataKeys.CONDITIONS), e);
        }

        return operation;
    }
}

