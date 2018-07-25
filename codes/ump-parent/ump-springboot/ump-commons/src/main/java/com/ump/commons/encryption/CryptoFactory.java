package com.ump.commons.encryption;

public enum CryptoFactory {
	INSTANCE;
	public IEncryptor getCrypto(String algorithm) {
		IEncryptor cryptoImpl = null;
		if ("des".equals(algorithm)) {

		} else if ("3des".equals(algorithm)) {
			cryptoImpl = new DesEncryptor();
		} else if ("aes".equals(algorithm)) {
			cryptoImpl = new AesEncryptor();
		} else if ("rsa".equals(algorithm)) {
			cryptoImpl = new RsaEncryptor();
		} else {
			throw new IllegalArgumentException("加密算法参数不正确");
		}
		return cryptoImpl;
	}
}
