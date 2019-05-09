package com.ump.commons.web;

public final class ResultUtil<T> {

	public static ResultRsp<?> error(String code, String msg) {
		return message(code, msg, null);
	}

	public static ResultRsp<?> error(String code, String msg, Object data) {
		return message(code, msg, data);
	}

	public static ResultRsp<Object> success() {
		return message(StatusCode.SUCCESS.code(), StatusCode.SUCCESS.message(), null);
	}

	public static ResultRsp<Object> success(String msg) {
		return message(StatusCode.SUCCESS.code(), msg, null);
	}

	public static ResultRsp<Object> success(String msg, Object data) {
		return message(StatusCode.SUCCESS.code(), msg, data);
	}

	public static ResultRsp<Object> message(RestStatus statusCode) {
		return message(statusCode.code(), statusCode.message(), null);
	}

	public static ResultRsp<Object> message(RestStatus statusCode, Object data) {
		return message(statusCode.code(), statusCode.message(), data);
	}

	private static ResultRsp<Object> message(String code, String msg, Object data) {
		ResultRsp<Object> ret = new ResultRsp<Object>();
		ret.setRsp(code, msg, data);
		return ret;
	}
}
