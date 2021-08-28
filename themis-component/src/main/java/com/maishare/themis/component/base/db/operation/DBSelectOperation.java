/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.operation;

import java.util.List;
import java.util.Map;

import com.maishare.themis.component.base.domain.CompareItem;
import com.maishare.themis.context.execution.ThemisTestExecution;

import lombok.Getter;
import lombok.Setter;

/**
 * 查询操作
 * @author moushaokun
 * @version @Id: DBSelectOperation.java, v 0.1 2020年03月19日 11:29 moushaokun Exp $
 */
public class DBSelectOperation extends AbstractDBOperation<List<Map<String, Object>>> {

    /** 执行数据库操作的条件选项 */
    @Getter
    @Setter
    private Map<String, CompareItem>       conditions;

    /** 执行数据库操作后，对结果进行比较的比较选项 */
    @Getter
    @Setter
    private List<Map<String, CompareItem>> comparisons;

    /** 执行数据库操作后，对结果进行比较的定位字段，可以理解为主键/联合主键 */
    @Getter
    @Setter
    private List<String>                   keys;

    public DBSelectOperation(ThemisTestExecution themisTestExecution) {
        super(themisTestExecution);
    }

    @Override
    public DBOperationType type() {
        return DBOperationType.SELECT;
    }
}
