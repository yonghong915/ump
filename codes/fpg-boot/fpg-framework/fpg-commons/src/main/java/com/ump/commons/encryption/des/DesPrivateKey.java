package com.ump.commons.encryption.des;

import com.ump.commons.encryption.internals.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class DesPrivateKey implements Key {
	@Getter
	@Setter
	private byte[] privKey;

	@Getter
	@Setter
	private boolean exportable;

	@Override
	public boolean isSymmetric() {
		return true;
	}

}
