/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.builder;

import com.maishare.themis.common.constants.LibraCompDBDataKeys;
import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.component.base.db.factory.DBOperationFactory;
import com.maishare.themis.component.base.db.operation.DBOperation;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.component.utils.DataCheckUtils;
import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.ExtensionLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * yaml与数据库操作对象的转换器
 * @author moushaokun
 * @version @Id: YamlDBOperationBuilder.java, v 0.1 2020年03月19日 15:14 moushaokun Exp $
 */
public class YamlDBOperationBuilder implements DBOperationBuilder<DBOperation> {

    @Override
    public boolean support(FileType fileType) {
        return FileType.YAML == fileType;
    }

    @Override
    public Map<String, List<DBOperation>> build(ThemisTestExecution themisTestExecution, TypeFile file) {
        List<Map<String,Object>> operations = file.getOriginal();

        Map<String, List<DBOperation>> dbOperationMapList = new HashMap<>();

        operations.forEach(operation -> {
            DataCheckUtils.checkKeyInMap(operation, LibraCompDBDataKeys.INDEX);
            DataCheckUtils.checkKeyInMap(operation, LibraCompDBDataKeys.DATA);

            List<DBOperation> dbOperations = new ArrayList<>();
            List<Map<String,Object>> dataMap = (List<Map<String, Object>>) operation.get(LibraCompDBDataKeys.DATA);

            dataMap.forEach(data -> {
                DataCheckUtils.checkKeyInMap(data, LibraCompDBDataKeys.TYPE);
                DataCheckUtils.checkKeyInMap(data, LibraCompDBDataKeys.TABLE_NAME);
                dbOperations.add(ExtensionLoader.getExtensionLoader(DBOperationFactory.class)
                    .getAdaptiveExtension()
                    .create(themisTestExecution, data, DBOperationType.get(data.get(LibraCompDBDataKeys.TYPE).toString()), file.getType()));
            });

            dbOperationMapList.put(operation.get(LibraCompDBDataKeys.INDEX).toString(), dbOperations);
        });

        return dbOperationMapList;
    }

}

