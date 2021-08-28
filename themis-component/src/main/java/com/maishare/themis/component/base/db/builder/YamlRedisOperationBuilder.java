/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.builder;

import com.maishare.themis.common.constants.LibraCompDBDataKeys;
import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.component.base.db.operation.RedisDeleteOperation;
import com.maishare.themis.component.base.db.operation.RedisOperation;
import com.maishare.themis.component.utils.DataCheckUtils;
import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.context.execution.ThemisTestExecution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * redis操作 yaml转换器
 * @author moushaokun
 * @version @Id: YamlRedisOperationBuilder.java, v 0.1 2020年03月25日 15:40 moushaokun Exp $
 */
public class YamlRedisOperationBuilder implements RedisOperationBuilder<RedisOperation> {

    @Override
    public boolean support(FileType fileType) {
        return FileType.YAML == fileType;
    }

    @Override
    public Map<String, List<RedisOperation>> build(ThemisTestExecution themisTestExecution, TypeFile file) {
        List<Map<String,Object>> operations = file.getOriginal();

        Map<String, List<RedisOperation>> operationMapList = new HashMap<>();

        operations.forEach(operation -> {
            DataCheckUtils.checkKeyInMap(operation, LibraCompDBDataKeys.INDEX);
            DataCheckUtils.checkKeyInMap(operation, LibraCompDBDataKeys.DATA);

            List<RedisOperation> redisOperations = new ArrayList<>();
            List<String> dataMap = (List<String>) operation.get(LibraCompDBDataKeys.DATA);

            //目前只支持DELETE操作，如果有其他操作，重构以下代码
            dataMap.forEach(data -> {
                RedisDeleteOperation redisDeleteOperation = new RedisDeleteOperation(themisTestExecution);
                redisDeleteOperation.setKey(data);
                redisOperations.add(redisDeleteOperation);
            });

            operationMapList.put(operation.get(LibraCompDBDataKeys.INDEX).toString(), redisOperations);
        });

        return operationMapList;
    }
}

