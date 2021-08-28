/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.common.exception;

public class LibraExpressionException extends ThemisException{
    public LibraExpressionException(Throwable t) {
        super(t);
    }

    public LibraExpressionException(String msg){
        super(msg);
    }

    public LibraExpressionException(String msg, Throwable t ){super(msg,t);}
}

