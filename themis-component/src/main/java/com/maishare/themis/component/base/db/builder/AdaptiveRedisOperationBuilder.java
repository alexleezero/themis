/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.builder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.common.exception.DBOperationException;
import com.maishare.themis.component.base.db.operation.RedisOperation;
import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.Adaptive;
import com.maishare.themis.extension.ExtensionLoader;

/**
 * redis操作对象转换器自适应扩展类
 * @author moushaokun
 * @version @Id: AdaptiveRedisOperationBuilder.java, v 0.1 2020年03月25日 15:39 moushaokun Exp $
 */
@Adaptive
public class AdaptiveRedisOperationBuilder implements RedisOperationBuilder {
    @Override
    public boolean support(FileType fileType) {
        return false;
    }

    @Override
    public Map<String, List<RedisOperation>> build(ThemisTestExecution themisTestExecution, TypeFile file) {
        Optional<RedisOperationBuilder> builderOptional = ExtensionLoader.getExtensionLoader(RedisOperationBuilder.class)
                .getSupportedExtensionInstances()
                .stream().filter(builder -> builder.support(file.getType()))
                .findFirst();

        if(!builderOptional.isPresent()){
            throw new DBOperationException(String.format("没有找到对应类型[%s]的转换器", file.getType()));
        }

        return builderOptional.get().build(themisTestExecution, file);
    }
}

