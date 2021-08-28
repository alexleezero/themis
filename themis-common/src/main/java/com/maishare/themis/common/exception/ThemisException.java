/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.common.exception;

public class ThemisException extends RuntimeException{

    public ThemisException(Throwable t){super(t);}

    public ThemisException(String msg){
        super(msg);
    }

    public ThemisException(String msg, Throwable t){super(msg, t);}
}

