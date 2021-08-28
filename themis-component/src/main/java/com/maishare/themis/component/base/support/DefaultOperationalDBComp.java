/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.support;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.maishare.themis.common.exception.ComponentException;
import com.maishare.themis.component.base.db.builder.OperationBuilder;
import com.maishare.themis.component.base.db.operation.Operation;
import com.maishare.themis.component.base.func.OperationalComp;
import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.ExtensionLoader;

/**
 * 可获取操作项的DB组件
 * @author moushaokun
 * @version @Id: DefaultOperationalDBComp.java, v 0.1 2020年03月24日 11:12 moushaokun Exp $
 */
public class DefaultOperationalDBComp implements OperationalComp {

    public List<Operation> operations(Class<? extends OperationBuilder> clazz, ThemisTestExecution themisTestExecution, TypeFile typeFile, String index) {
        Map<String, List<Operation>> operationMapList =
                ExtensionLoader.getExtensionLoader(clazz)
                        .getAdaptiveExtension().build(themisTestExecution, typeFile);

        if (operationMapList.get(index) == null) {
            throw new ComponentException(String.format("不能识别的数据索引：[%s]", index));
        }

        return operationMapList.get(index);
    }

    @Override
    public void checkSupportType(String scene, List<Operation> operations, Enum... supportType) {
        List<Enum> dbOperationTypes = Arrays.asList(supportType);

        operations.forEach(operation -> {
            if (!dbOperationTypes.contains(operation.type())) {
                throw new ComponentException(String.format("%s阶段不允许[%s]操作", scene, operation.type()));
            }
        });
    }

}

