package com.ump.commons.crypto;

import org.apache.commons.codec.digest.DigestUtils;

public final class CipherUtils {
    private CipherUtils() {}
	public static String encrypt4SHA256(String username, String password, String salt) {
		return DigestUtils.sha256Hex(username + salt + password);
	}
   
}