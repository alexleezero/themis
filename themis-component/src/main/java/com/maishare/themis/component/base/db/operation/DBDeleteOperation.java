/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.operation;

import java.util.Map;

import com.maishare.themis.component.base.domain.CompareItem;
import com.maishare.themis.context.execution.ThemisTestExecution;

import lombok.Getter;
import lombok.Setter;

/**
 * 删除操作
 * @author moushaokun
 * @version @Id: DBDeleteOperation.java, v 0.1 2020年03月19日 11:29 moushaokun Exp $
 */
public class DBDeleteOperation extends AbstractDBOperation<Integer> {

    /** 执行数据库操作的条件选项 */
    @Getter
    @Setter
    private Map<String, CompareItem> conditions;

    public DBDeleteOperation(ThemisTestExecution themisTestExecution) {
        super(themisTestExecution);
    }

    @Override
    public DBOperationType type() {
        return DBOperationType.DELETE;
    }
}
