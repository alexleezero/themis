/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.common.exception;

/**
 * 参数异常定义
 * @author hejianbing
 * @version @Id: ParamException.java, v 0.1 2020年03月24日 12:45 hejianbing Exp $
 */
public class ParamException extends ComponentException {
	
	public ParamException(Throwable t) {
		super(t);
	}
	
	public ParamException(String msg){
		super(msg);
	}
}