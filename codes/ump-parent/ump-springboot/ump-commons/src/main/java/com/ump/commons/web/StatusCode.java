package com.ump.commons.web;

public enum StatusCode implements RestStatus {
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
