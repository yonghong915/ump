package com.ump.commons.encryption;

import org.apache.commons.codec.digest.DigestUtils;

public final class CipherUtils {
	private CipherUtils() {
	}

	public static String encrypt4SHA256(String username, String password, String salt) {
		return DigestUtils.sha256Hex(username + salt + password);
	}

	public static String encrypt4DES(String plaintext) {
		IEncryptor encryptor = CryptoFactory.INSTANCE.getCrypto("3des");
		return encryptor.encrypt(plaintext);
	}
}