/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.processor;

import com.maishare.themis.common.exception.DBOperationException;
import com.maishare.themis.component.base.db.operation.DBOperation;
import com.maishare.themis.component.base.db.operation.OperationType;
import com.maishare.themis.extension.Adaptive;
import com.maishare.themis.extension.ExtensionLoader;

import java.util.Optional;

/**
 * 数据库操作自适应配置配
 * @author moushaokun
 * @version @Id: AdaptiveDBOperationProcessor.java, v 0.1 2020年03月19日 11:07 moushaokun Exp $
 */
@Adaptive
public class AdaptiveDBOperationProcessor implements DBOperationProcessor<DBOperation, Object> {

    @Override
    public boolean support(OperationType operationType) {
        return false;
    }
    
    @Override
    public Object process(DBOperation operation) {
        Optional<DBOperationProcessor> processorOptional = ExtensionLoader.getExtensionLoader(DBOperationProcessor.class)
                .getSupportedExtensionInstances()
                .stream().filter(processor -> processor.support(operation.type()))
                .findFirst();

        if(!processorOptional.isPresent()){
            throw new DBOperationException(String.format("没有找到对应类型[%s]的处理器", operation.type()));
        }

        return processorOptional.get().process(operation);
    }
}

