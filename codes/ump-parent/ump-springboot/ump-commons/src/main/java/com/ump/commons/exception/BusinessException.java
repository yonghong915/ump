package com.ump.commons.exception;

import com.ump.commons.web.RestStatus;

import lombok.Getter;
import lombok.Setter;

public class BusinessException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String code;

	@Getter
	@Setter
	private String msg;

	public BusinessException() {
		super();
	}

	public BusinessException(String errCode) {
		super(errCode);
	}

	public BusinessException(Throwable exception) {
		super(exception);
	}

	public BusinessException(String errCode, Throwable exception) {
		super(errCode, exception);
	}

	public BusinessException(RestStatus restStatus) {
		super(restStatus.message());
		this.code = restStatus.code();
		this.msg = restStatus.message();
	}

	// public String toString() {
	// String exceptionName = getClass().getName();
	// String errorCode = getLocalizedMessage();
	// StringBuffer sb = new StringBuffer(exceptionName);
	// if (null != errorCode && !"".equals(errorCode)) {
	// sb.append("[errCode:").append(errorCode).append(",errmsg:");
	// sb.append(MessageFormat.format(getErrorMsg(this.getErrCode()),
	// getParams())).append("]");
	// }
	// return sb.toString();
	// }
}
