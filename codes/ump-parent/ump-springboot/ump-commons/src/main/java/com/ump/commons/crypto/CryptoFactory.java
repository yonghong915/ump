package com.ump.commons.crypto;

public enum CryptoFactory {
	INSTANCE;
	public ICrypto getCrypto(String algorithm) {
		ICrypto cryptoImpl = null;
		if ("des".equals(algorithm)) {

		} else if ("3des".equals(algorithm)) {
			cryptoImpl = new DESCrypto();
		} else if ("aes".equals(algorithm)) {
			cryptoImpl = new AESCrypto();
		} else if ("rsa".equals(algorithm)) {
			cryptoImpl = new RSACrypto();
		} else {
			throw new IllegalArgumentException("加密算法参数不正确");
		}
		return cryptoImpl;
	}
}
