/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.builder;

import com.maishare.themis.component.base.db.operation.Operation;
import com.maishare.themis.extension.SPI;

/**
 * redis 操作转换器
 * @author moushaokun
 * @version @Id: RedisOperationBuilder.java, v 0.1 2020年03月25日 15:30 moushaokun Exp $
 */
@SPI
public interface RedisOperationBuilder<T extends Operation> extends OperationBuilder<T> {

}

