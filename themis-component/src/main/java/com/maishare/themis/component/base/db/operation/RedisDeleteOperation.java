/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.operation;

import com.maishare.themis.component.base.db.execoter.RedisExecutor;
import com.maishare.themis.context.execution.ThemisTestExecution;

import lombok.Getter;
import lombok.Setter;

/**
 * redis 删除操作
 * @author moushaokun
 * @version @Id: RedisDeleteOperation.java, v 0.1 2020年03月25日 15:36 moushaokun Exp $
 */
public class RedisDeleteOperation extends AbstractRedisOperation<Boolean>{

    @Getter
    @Setter
    private String key;

    public RedisDeleteOperation(ThemisTestExecution themisTestExecution) {
        super(themisTestExecution);
    }

    @Override
    public RedisOperationType type() {
        return RedisOperationType.DELETE;
    }

    @Override
    public Boolean execute() {
        return RedisExecutor.getInstance(
            getThemisTestExecution().getThemisContext().getThemisConfig().getRedisConfiguration())
            .delete(this.key);
    }
}

