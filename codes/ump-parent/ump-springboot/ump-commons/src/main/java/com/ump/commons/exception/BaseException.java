/*
 * 
 */
package com.ump.commons.exception;

/**
 * Base exception class of all exception classes
 * 
 * @author fangyh
 * @date 2018-08-05 09:45:05
 * @version 1.0.0
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 0x1a8e9f24dbb2c54bL;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
}
