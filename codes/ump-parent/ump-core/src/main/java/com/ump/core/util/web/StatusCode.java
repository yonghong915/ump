package com.ump.core.util.web;

import com.google.common.collect.ImmutableMap;

public enum StatusCode implements RestStatus {
	/** 操作成功 */
	SUCCESS("000000", "操作成功"),

	/** 操作失败 */
	FAIL("-999999", "操作失败");

	private final String code;

	private final String message;

	StatusCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public String message() {
		return message;
	}

	private static final ImmutableMap<String, StatusCode> CACHE;
	static {
		final ImmutableMap.Builder<String, StatusCode> builder = ImmutableMap.builder();
		for (StatusCode statusCode : values()) {
			builder.put(statusCode.code(), statusCode);
		}
		CACHE = builder.build();
	}

	public static StatusCode valueOfCode(String code) {
		final StatusCode status = CACHE.get(code);
		if (status == null) {
			throw new IllegalArgumentException("No matching constant for [" + code + "]");
		}
		return status;
	}

}
