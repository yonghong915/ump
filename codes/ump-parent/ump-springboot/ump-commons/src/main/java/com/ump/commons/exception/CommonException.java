package com.ump.commons.exception;

import java.security.GeneralSecurityException;

import com.ump.commons.web.RestStatus;

import lombok.Getter;
import lombok.Setter;

public class CommonException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String code;

	public CommonException(String string, GeneralSecurityException e) {
		super(string);
	}

	public CommonException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public CommonException(RestStatus restStatus) {
		super(restStatus.message());
		this.code = restStatus.code();
	}

}
