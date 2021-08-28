package com.maishare.themis.common.exception;

public class AssertMatcherException extends ThemisException {

    public AssertMatcherException(Throwable t) {
        super(t);
    }

    public AssertMatcherException(String msg){
        super(msg);
    }
    public AssertMatcherException(String msg,Throwable t){
        super(msg,t);
    }
}
