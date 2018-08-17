package com.ump.commons.exception;

import com.ump.commons.web.RestStatus;
import com.ump.commons.web.StatusCode;
import lombok.Getter;

/**
 * Business exception of all business throw
 * 
 * @author fangyh
 * @date 2018-08-05 09:46:05
 * @version 1.0.0
 */
public class BusinessException extends BaseException {
	private static final long serialVersionUID = 1L;
	@Getter
	private final String code;

	@Getter
	private final String msg;

	public BusinessException(String errCode, String message) {
		super(message);
		this.code = errCode;
		this.msg = message;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.code = StatusCode.FAIL.code();
		this.msg = message;
	}

	public BusinessException(Throwable exception) {
		super(exception);
		this.code = StatusCode.FAIL.code();
		this.msg = StatusCode.FAIL.message();
	}

	public BusinessException(RestStatus restStatus) {
		super(restStatus.message());
		this.code = restStatus.code();
		this.msg = restStatus.message();
	}

	public BusinessException(RestStatus restStatus, Throwable cause) {
		super(restStatus.message(), cause);
		this.code = restStatus.code();
		this.msg = restStatus.message();
	}

	@Override
	public String toString() {
		return "{rspCode:" + code + ",rspMsg:" + msg + "}";
	}
}
