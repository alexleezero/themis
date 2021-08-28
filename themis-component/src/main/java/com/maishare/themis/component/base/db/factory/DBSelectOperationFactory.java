/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.factory;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.maishare.themis.common.constants.LibraCompDBDataKeys;
import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.common.exception.DBOperationException;
import com.maishare.themis.common.exception.FileParseException;
import com.maishare.themis.component.base.db.operation.DBOperation;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.component.base.db.operation.DBSelectOperation;
import com.maishare.themis.component.base.domain.CompareItem;
import com.maishare.themis.component.utils.DataCheckUtils;
import com.maishare.themis.context.execution.ThemisTestExecution;

/**
 * 查询操作工厂
 * @author moushaokun
 * @version @Id: DBSelectOperationFactory.java, v 0.1 2020年03月20日 14:56 moushaokun Exp $
 */
public class DBSelectOperationFactory extends AbstractYamlDBOperationFactory {

    @Override
    public boolean support(DBOperationType operationType) {
        return DBOperationType.SELECT == operationType;
    }

    @Override
    public DBOperation create(ThemisTestExecution themisTestExecution, Map<String, Object> data, DBOperationType operationType, FileType type) {
        DBSelectOperation operation = new DBSelectOperation(themisTestExecution);

        operation.setTableName(getValue(themisTestExecution, data, LibraCompDBDataKeys.TABLE_NAME).toString());

        if (data.get(LibraCompDBDataKeys.COMPARISONS) != null) {
            try {
                Pair<List<String>, List<Map<String, CompareItem>>> keysAndCompareItems
                        = toCompareItemListMap(getValue(themisTestExecution, data, LibraCompDBDataKeys.COMPARISONS));
                operation.setKeys(keysAndCompareItems.getLeft());
                operation.setComparisons(keysAndCompareItems.getRight());
            } catch (Exception e) {
                throw new DBOperationException(String.format("数据项[%s]格式不正确", LibraCompDBDataKeys.COMPARISONS), e);
            }

        } else if (data.get(LibraCompDBDataKeys.CHECK_ROWS) != null) {
            operation.setCheckRows(getValue(themisTestExecution, data, LibraCompDBDataKeys.CHECK_ROWS) == null ? null
                    : Integer.valueOf(getValue(themisTestExecution, data, LibraCompDBDataKeys.CHECK_ROWS).toString()));

        } else {
            throw new FileParseException(String.format("数据文件缺少关键数据项，[%s]和[%s]应至少包含一个", LibraCompDBDataKeys.COMPARISONS, LibraCompDBDataKeys.CHECK_ROWS));
        }

        DataCheckUtils.checkKeyInMap(data, LibraCompDBDataKeys.CONDITIONS);
        try {
            operation.setConditions(toCompareItemMap(getValue(themisTestExecution, data, LibraCompDBDataKeys.CONDITIONS)));
        } catch (Exception e) {
            throw new DBOperationException(String.format("数据项[%s]格式不正确", LibraCompDBDataKeys.CONDITIONS), e);
        }

        return operation;
    }
}

