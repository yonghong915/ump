package com.ump.commons.encryption.type;

public enum EncryptAlgorithm {
	DES("DES"), DESEDE("DESede"), AES("AES"), RSA("RSA");
	private String algorithm;

	EncryptAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String value() {
		return this.algorithm;
	}
}
