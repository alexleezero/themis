/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.builder;

import com.maishare.themis.component.base.db.operation.Operation;
import com.maishare.themis.extension.SPI;

/**
 * 数据库操作转换器
 * @author moushaokun
 * @version @Id: DBOperationBuilder.java, v 0.1 2020年03月19日 15:04 moushaokun Exp $
 */
@SPI
public interface DBOperationBuilder<T extends Operation>  extends OperationBuilder<T> {

}

