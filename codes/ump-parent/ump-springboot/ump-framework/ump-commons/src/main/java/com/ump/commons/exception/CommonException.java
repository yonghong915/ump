package com.ump.commons.exception;

import com.ump.commons.web.RestStatus;
import com.ump.commons.web.StatusCode;
import lombok.Getter;

/**
 * Commons exception tool
 * 
 * @author fangyh
 * @date 2018-08-05 09:58:05
 * @version 1.0.0
 */
public class CommonException extends BaseException {
	private static final long serialVersionUID = 1L;
	@Getter
	private final String code;

	public CommonException(String msg) {
		super(msg);
		this.code = StatusCode.FAIL.code();
	}

	public CommonException(String msg, Throwable cause) {
		super(msg, cause);
		this.code = StatusCode.FAIL.code();
	}

	public CommonException(RestStatus restStatus) {
		super(restStatus.message());
		this.code = restStatus.code();
	}

	public CommonException(RestStatus restStatus, Throwable cause) {
		super(restStatus.message(), cause);
		this.code = restStatus.code();
	}

	@Override
	public String toString() {
		return "{rspCode:" + code + ",rspMsg:" + getMessage() + "}";
	}
}
