/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.processor;

import com.maishare.themis.component.base.db.operation.DBOperation;
import com.maishare.themis.component.base.db.operation.OperationType;
import com.maishare.themis.extension.SPI;

/**
 * 数据库操作处理器接口
 * @author moushaokun
 * @version @Id: DBOperation.java, v 0.1 2020年03月19日 10:42 moushaokun Exp $
 */
@SPI
public interface DBOperationProcessor<T extends DBOperation, R> {

    boolean support(OperationType operationType);

    R process(T operation);
}

