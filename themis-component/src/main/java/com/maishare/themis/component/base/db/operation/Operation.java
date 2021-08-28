/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.component.base.db.operation;

/**
 * 操作项接口
 * @author moushaokun
 * @version @Id: Operation.java, v 0.1 2020年03月25日 16:10 moushaokun Exp $
 */
public interface Operation<R> {
    
    OperationType type();

    R execute();
}

