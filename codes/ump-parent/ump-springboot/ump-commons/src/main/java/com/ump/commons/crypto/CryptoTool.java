package com.ump.commons.crypto;

import org.apache.commons.codec.digest.DigestUtils;

public class CryptoTool {
	private CryptoTool() {
	}

	public static String getMd5(String code) {
		return DigestUtils.md5Hex(code);
	}
}
