/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.factory;

import java.util.Map;

import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.component.base.db.operation.DBOperation;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.SPI;

/**
 * DB操作工厂
 * @author moushaokun
 * @version @Id: DBOperationFactory.java, v 0.1 2020年03月20日 14:45 moushaokun Exp $
 */
@SPI
public interface DBOperationFactory {

    boolean support(DBOperationType operationType, FileType type);

    DBOperation create(ThemisTestExecution themisTestExecution, Map<String, Object> data, DBOperationType operationType, FileType type);
}

