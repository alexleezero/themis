/**
 * bravo.org
 * Copyright (c) 2018-2019 ALL Rights Reserved
 */
package com.maishare.themis.common.exception;

public class FileParseException extends ThemisException {
	
	public FileParseException(String message) {
		super(message);
	}
	
	public FileParseException(Throwable t) {
		super(t);
	}
	public FileParseException(String message, Throwable t) {
		super(message,t);
	}
}

