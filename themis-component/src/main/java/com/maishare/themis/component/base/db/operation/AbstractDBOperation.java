/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.operation;

import com.maishare.themis.component.base.db.processor.DBOperationProcessor;
import com.maishare.themis.context.execution.ThemisTestExecution;
import com.maishare.themis.extension.ExtensionLoader;

import lombok.Getter;
import lombok.Setter;

/**
 * DB操作模板
 * @author moushaokun
 * @version @Id: AbstractDBOperation.java, v 0.1 2020年03月19日 11:02 moushaokun Exp $
 */
public abstract class AbstractDBOperation<R> implements DBOperation<R> {

    @Getter
    @Setter
    private ThemisTestExecution themisTestExecution;

    /** 表名 */
    @Getter
    @Setter
    private String              tableName;

    /** 执行操作后检查执行的行数 */
    @Getter
    @Setter
    private Integer             checkRows;

    public AbstractDBOperation(ThemisTestExecution themisTestExecution) {
        setThemisTestExecution(themisTestExecution);
    }

    @Override
    public R execute() {
        DBOperationProcessor processor = ExtensionLoader
            .getExtensionLoader(DBOperationProcessor.class).getAdaptiveExtension();

        return (R) processor.process(this);
    }
}
