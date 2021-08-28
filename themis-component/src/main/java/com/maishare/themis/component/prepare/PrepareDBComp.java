/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.prepare;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maishare.themis.common.constants.LibraCompNameConstants;
import com.maishare.themis.component.base.db.builder.DBOperationBuilder;
import com.maishare.themis.component.base.db.operation.DBOperationType;
import com.maishare.themis.component.base.db.operation.Operation;
import com.maishare.themis.component.base.func.OperationalComp;
import com.maishare.themis.component.base.func.TypeFileFilter;
import com.maishare.themis.component.base.support.DefaultOperationalDBComp;
import com.maishare.themis.component.base.support.DefaultTypeFileFilter;
import com.maishare.themis.component.param.CommonIndexCompParam;
import com.maishare.themis.context.domain.TypeFile;
import com.maishare.themis.context.execution.ThemisTestExecution;

/**
 * Prepare db 组件
 * @author moushaokun
 * @version @Id: PrepareDBComp.java, v 0.1 2020年03月11日 13:38 moushaokun Exp $
 */
public class PrepareDBComp extends PrepareComponent<CommonIndexCompParam> {

    private static final Logger          LOGGER          = LoggerFactory
        .getLogger(PrepareDBComp.class);
    private static final TypeFileFilter  typeFileFilter  = new DefaultTypeFileFilter();
    private static final OperationalComp operationalComp = new DefaultOperationalDBComp();

    @Override
    public void act(ThemisTestExecution themisTestExecution, CommonIndexCompParam commonIndexCompParam) {
        LOGGER.debug("准备数据，参数: " + commonIndexCompParam.toString());

        TypeFile typeFile = typeFileFilter.doFilter(themisTestExecution.getLibraFile().getLibraPrepareFiles(),
                commonIndexCompParam.getFileName());

        List<Operation> operations = operationalComp.operations(DBOperationBuilder.class,
            themisTestExecution, typeFile, commonIndexCompParam.getIndex());

        operationalComp.checkSupportType("准备数据", operations, DBOperationType.INSERT, DBOperationType.DELETE, DBOperationType.UPDATE);

        operations.forEach(Operation::execute);
    }

    @Override
    public String name() {
        return LibraCompNameConstants.DB;
    }
}

