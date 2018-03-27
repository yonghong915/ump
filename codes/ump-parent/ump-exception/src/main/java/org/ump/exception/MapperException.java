package org.ump.exception;

public class MapperException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;
	private Object[] params;

	public MapperException() {
	}

	public MapperException(String errCode) {
		super(errCode);
		this.errCode = errCode;
	}

	public MapperException(String errCode, Object[] params) {
		super(errCode);
		this.errCode = errCode;
		this.params = params;
	}

	public MapperException(Throwable exception) {
		super(exception);
		this.errMsg = exception.getMessage();
	}

	public MapperException(Throwable exception, Object[] params) {
		super(exception);
		this.errMsg = exception.getMessage();
		this.params = params;
	}

	public MapperException(String errCode, Throwable exception) {
		super(errCode, exception);
		this.errCode = errCode;
		this.errMsg = exception.getMessage();
	}

	public MapperException(String errCode, Throwable exception, Object[] params) {
		super(errCode, exception);
		this.errCode = errCode;
		this.errMsg = exception.getMessage();
		this.params = params;
	}

	public MapperException(String errCode, String errMsg, Throwable exception) {
		super(errMsg, exception);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public MapperException(String errCode, String errMsg, Throwable exception, Object[] params) {
		super(errMsg, exception);
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.params = params;
	}

	public MapperException(String errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public MapperException(String errCode, String errMsg, Object[] params) {
		super(errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.params = params;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}
