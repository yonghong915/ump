package com.ump.commons.encryption;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @author fangyh
 * @date 2018-08-17 22:08:17
 * @version 1.0.0
 */
public final class CipherUtils {
	private CipherUtils() {
	}

	public static String encrypt4SHA256(String username, String password, String salt) {
		return DigestUtils.sha256Hex(username + "{" + password + "}" + salt);
	}
}