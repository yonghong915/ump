package com.ump.exception;

public class CaptchaException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CaptchaException(String msg) {
		super(msg);
	}

	public CaptchaException(String msg, Throwable t) {
		super(msg, t);
	}
}
