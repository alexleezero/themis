/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.factory;

import java.util.Map;
import java.util.Optional;

import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.common.exception.DBOperationException;
import com.maishare.themis.component.base.db.operation.DBOperation;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.Adaptive;
import com.maishare.themis.extension.ExtensionLoader;

/**
 * DB操作工厂自适应扩展类
 * @author moushaokun
 * @version @Id: AdaptiveDBOperationFactory.java, v 0.1 2020年03月20日 14:46 moushaokun Exp $
 */
@Adaptive
public class AdaptiveDBOperationFactory implements DBOperationFactory{
    @Override
    public boolean support(DBOperationType operationType, FileType fileType) {
        return false;
    }

    @Override
    public DBOperation create(ThemisTestExecution themisTestExecution, Map<String, Object> data, DBOperationType operationType, FileType type) {
        Optional<DBOperationFactory> builderOptional = ExtensionLoader.getExtensionLoader(DBOperationFactory.class)
                .getSupportedExtensionInstances()
                .stream().filter(factory -> factory.support(operationType, type))
                .findFirst();

        if(!builderOptional.isPresent()){
            throw new DBOperationException(String.format("没有找到对应类型[%s]操作的生产工厂", operationType));
        }

        return builderOptional.get().create(themisTestExecution, data, operationType, type);
    }
}

