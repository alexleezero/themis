/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.operation;

import com.maishare.themis.context.execution.ThemisTestExecution;

import lombok.Getter;
import lombok.Setter;

/**
 * redis操作模板
 * @author moushaokun
 * @version @Id: AbstractRedisOperation.java, v 0.1 2020年03月25日 15:34 moushaokun Exp $
 */
public abstract class AbstractRedisOperation<R> implements RedisOperation<R> {

    public AbstractRedisOperation(ThemisTestExecution themisTestExecution){
        this.themisTestExecution = themisTestExecution;
    }

    @Getter
    @Setter
    private ThemisTestExecution themisTestExecution;
}

