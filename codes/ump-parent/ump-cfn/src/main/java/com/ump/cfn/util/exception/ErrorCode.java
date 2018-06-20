package com.ump.cfn.util.exception;

import org.ump.exception.ExceptionCode;

import com.ump.commons.CommUtils;

public enum ErrorCode implements ExceptionCode {
	E_1001("1002");

	ErrorCode(String errorCode) {
		this.errorCode = errorCode;
		this.message = CommUtils.getErrorMsg(errorCode);
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
