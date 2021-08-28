/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.common.exception;

public class ComponentException extends ThemisException{

    public ComponentException(Throwable t) {
        super(t);
    }

    public ComponentException(String msg){
        super(msg);
    }
    public ComponentException(String msg,Throwable t){
        super(msg,t);
    }
}

