package com.ump.exception;

import com.ump.exception.ExceptionCode;

public enum ErrorCode implements ExceptionCode {
	E_1001("1002");

	ErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	private String errorCode;

	private String message;

	public String getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}
}
