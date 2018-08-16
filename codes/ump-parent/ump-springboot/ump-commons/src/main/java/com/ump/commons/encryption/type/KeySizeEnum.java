package com.ump.commons.encryption.type;

public enum KeySizeEnum {
	DES(56), DESede(168), AES(128), RSA(2048);
	private int keySize;

	KeySizeEnum(int keySize) {
		this.keySize = keySize;
	}

	public int value() {
		return this.keySize;
	}
}
