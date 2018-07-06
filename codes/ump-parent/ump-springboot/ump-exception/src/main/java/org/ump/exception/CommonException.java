package org.ump.exception;

import java.security.GeneralSecurityException;

public class CommonException extends BaseException {

	public CommonException(String string, GeneralSecurityException e) {
		super(string);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
