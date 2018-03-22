package com.ump.exception;

public class BusinessException extends MapperException {
	private static final long serialVersionUID = -3876502758804606346L;

	public BusinessException() {
	}

	public BusinessException(String errCode) {
		super(errCode);
	}

	public BusinessException(String errCode, Object[] params) {
		super(errCode, params);
	}

	public BusinessException(Throwable exception) {
		super(exception);
	}

	public BusinessException(Throwable exception, Object[] params) {
		super(exception, params);
	}

	public BusinessException(String errCode, Throwable exception) {
		super(errCode, exception);
	}

	public BusinessException(String errCode, Throwable exception, Object[] params) {
		super(errCode, exception, params);
	}

	public BusinessException(String errCode, String errMsg, Throwable exception) {
		super(errCode, errMsg, exception);
	}

	public BusinessException(String errCode, String errMsg, Throwable exception, Object[] params) {
		super(errCode, errMsg, exception, params);
	}

	public BusinessException(String errCode, String errMsg) {
		super(errCode, errMsg);
	}

	public BusinessException(String errCode, String errMsg, Object[] params) {
		super(errCode, errMsg, params);
	}

}