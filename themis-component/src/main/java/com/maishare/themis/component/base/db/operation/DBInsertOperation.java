/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.operation;

import java.util.Map;

import com.maishare.themis.context.execution.ThemisTestExecution;

import lombok.Getter;
import lombok.Setter;

/**
 * 插入操作
 * @author moushaokun
 * @version @Id: DBInsertOperation.java, v 0.1 2020年03月19日 10:54 moushaokun Exp $
 */
public class DBInsertOperation extends AbstractDBOperation<Integer> {

    /** 执行数据库操作时，需要设置值的选项 */
    @Getter
    @Setter
    private Map<String, Object> sets;


    public DBInsertOperation(ThemisTestExecution themisTestExecution) {
        super(themisTestExecution);
    }

    @Override
    public DBOperationType type() {
        return DBOperationType.INSERT;
    }
}
