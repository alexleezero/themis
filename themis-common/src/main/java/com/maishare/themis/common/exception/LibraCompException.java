/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.common.exception;

/**
 * libra组件异常
 *
 * @author hejianbing
 * @version @Id: LibraCompException.java, v 0.1 2020年03月13日 13:54 hejianbing Exp $
 */
public class LibraCompException extends ThemisException {
	
	public LibraCompException(Throwable t) {
		super(t);
	}
	
	public LibraCompException(String msg) {
		super(msg);
	}
	public LibraCompException(String msg,Throwable t) {
		super(msg,t);
	}

	
}