package org.ump.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

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

	public BusinessException(ExceptionCode errorCode, Object... params) {
		super(errorCode.getErrorCode());
		setParams(params);
	}

	public BusinessException(ExceptionCode errorCode, Throwable exception, Object... params) {
		super(errorCode.getErrorCode(), exception);
		setParams(params);
	}

	public String getMsg() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(this.getErrCode()).append("]");
		sb.append(MessageFormat.format(getErrorMsg(this.getErrCode()), getParams()));
		return sb.toString();
	}

	private ResourceBundle getLocalResourceBundle(String resFile) {
		Locale myLocale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle(resFile, myLocale);
		return bundle;
	}

	private String getErrorMsg(String errorCode) {
		try {
			return getLocalResourceBundle("config.messages.errorMsg").getString(errorCode);
		} catch (Exception e) {
			return "";
		}
	}
}