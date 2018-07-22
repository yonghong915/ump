package com.ump.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

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
		this.errMsg = getErrorMsg(errCode);
	}

	public MapperException(String errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
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

	private static ResourceBundle getLocalResourceBundle(String resFile) {
		Locale myLocale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle(resFile, myLocale);
		return bundle;
	}

	protected static String getErrorMsg(String errorCode) {
		try {
			return getLocalResourceBundle("config.messages.errorMsg").getString(errorCode);
		} catch (Exception e) {
			return "";
		}
	}

	public String toString() {
		String exceptionName = getClass().getName();
		String errorCode = getLocalizedMessage();
		StringBuffer sb = new StringBuffer(exceptionName);
		if (null != errorCode && !"".equals(errorCode)) {
			sb.append("[errCode:").append(errorCode).append(",errMsg:");
			sb.append(MessageFormat.format(getErrMsg(), getParams())).append("]");
		}
		return sb.toString();
	}
}
