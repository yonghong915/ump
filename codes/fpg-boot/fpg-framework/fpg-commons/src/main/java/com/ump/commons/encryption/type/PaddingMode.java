package com.ump.commons.encryption.type;

public enum PaddingMode {
	PKCS5PADDING("PKCS5Padding"), NOPADDING("NoPadding"), ZEROSPADDING("ZerosPadding"), PKCS1PADDING("PKCS1Padding");
	private String padMode;

	PaddingMode(String padMode) {
		this.padMode = padMode;
	}

	public String value() {
		return this.padMode;
	}
}
