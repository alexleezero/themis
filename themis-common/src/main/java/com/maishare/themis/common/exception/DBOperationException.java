/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.common.exception;

/**
 * 数据库操作异常
 * @author moushaokun
 * @version @Id: DBOperationException.java, v 0.1 2020年03月19日 11:16 moushaokun Exp $
 */
public class DBOperationException extends ThemisException{

    public DBOperationException(Throwable t) {
        super(t);
    }

    public DBOperationException(String msg) {
        super(msg);
    }

    public DBOperationException(String msg, Throwable t) {
        super(msg, t);
    }
}

