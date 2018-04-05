package com.ump.commons.crypto;

import org.apache.commons.codec.digest.DigestUtils;

public final class CipherUtils {

	public static String encrypt4SHA256(String username, String password, String salt) {
		String encrypedPwd = DigestUtils.sha256Hex(username + salt + password);
		return encrypedPwd;
	}
     public static void main(String[] args) {
		System.out.println(encrypt4SHA256("fangyh", "123456", "123456"));
	}
}