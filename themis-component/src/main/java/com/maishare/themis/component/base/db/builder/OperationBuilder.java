/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.builder;

import java.util.List;
import java.util.Map;

import com.maishare.themis.common.enums.FileType;
import com.maishare.themis.component.base.db.operation.Operation;
import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.context.execution.ThemisTestExecution;

/**
 * 操作生成器
 * @author moushaokun
 * @version @Id: OperationBuilder.java, v 0.1 2020年03月25日 16:25 moushaokun Exp $
 */
public interface OperationBuilder<T extends Operation> {

    boolean support(FileType fileType);

    Map<String, List<T>> build(ThemisTestExecution themisTestExecution, TypeFile file);
}

