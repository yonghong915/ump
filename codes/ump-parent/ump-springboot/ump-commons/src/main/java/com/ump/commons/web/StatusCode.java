package com.ump.commons.web;

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
	ENCRYPT_FAIL("E990000", "failed to encrypt"),

	/** 解密失败 */
	DECRYPT_FAIL("E990001", "failed to decrypt"),

	/***/
	KEY_LENGTH_INSUFFICIENT("E990002", "key length insufficient");

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

}
