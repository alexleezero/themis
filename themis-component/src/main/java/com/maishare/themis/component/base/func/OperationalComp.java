/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.func;

import java.util.List;

import com.maishare.themis.component.base.db.builder.OperationBuilder;
import com.maishare.themis.component.base.db.operation.Operation;
import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.context.execution.ThemisTestExecution;

/**
 * 可获取操作项的组件
 * @author moushaokun
 * @version @Id: OperationalDBComp.java, v 0.1 2020年03月19日 15:35 moushaokun Exp $
 */
public interface OperationalComp {

    List<Operation> operations(Class<? extends OperationBuilder> builder, ThemisTestExecution themisTestExecution, TypeFile typeFile, String index);

    void checkSupportType(String scene, List<Operation> operations, Enum... supportType);

}
