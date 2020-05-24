package com.ump.commons.encryption.rsa;

import com.ump.commons.encryption.internals.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class RsaPrivateKey implements Key {
	@Getter
	@Setter
	private byte[] privKey;

	@Getter
	@Setter
	private byte[] publicKey;

	@Getter
	@Setter
	private boolean exportable;

	@Override
	public boolean isSymmetric() {
		return false;
	}
}
