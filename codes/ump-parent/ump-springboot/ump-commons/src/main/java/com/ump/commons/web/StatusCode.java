package com.ump.commons.web;

import com.google.common.collect.ImmutableMap;

/**
 * Response status Enum Class
 * 
 * @author fangyh
 * @date 2018-08-05 11:26:05
 * @version 1.0.0
 */
public enum StatusCode implements RestStatus {
	/** 操作成功 */
	SUCCESS("000000", "SUCCESS"),

	/** 操作失败 */
	FAIL("E999999", "FAILURE"),

	/** 加密失败 */
	GENKEY_FAIL("E990000", "failed to gen key"),

	/** 加密失败 */
	ENCRYPT_FAIL("E990001", "failed to encrypt"),

	/** 解密失败 */
	DECRYPT_FAIL("E990002", "failed to decrypt"),

	/***/
	KEY_LENGTH_INSUFFICIENT("E990003", "key length insufficient"),

	/***/
	PACK_XML_EXCEPTION("E990004", "组装XML异常"),

	/***/
	UNPACK_XML_EXCEPTION("E990005", "解包XML异常"),

	/** 加载文件异常 */
	ESB_REQ_EXCEPTION("E990006", "ESB异常"),

	/** 空指针异常 */
	NULLPOINTER("E000001", "发生空指针异常"),

	/** 请求参数类型不匹配 */
	ILLEGAL_ARGUMENT("E000002", "请求参数类型不匹配"),

	/** 数据库访问异常 */
	DB_EXCEPTION("E000003", "数据库访问异常"),

	/** 加载文件异常 */
	LOAD_FILE_EXCEPTION("E000004", "加载文件异常"),

	/** 其他异常 */
	OTHER_EXCEPTION("E000009", "其他异常");

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
