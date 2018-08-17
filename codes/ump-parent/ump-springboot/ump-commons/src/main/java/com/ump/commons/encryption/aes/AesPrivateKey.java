package com.ump.commons.encryption.aes;

import com.ump.commons.encryption.internals.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author fangyh
 * @date 2018-08-15 22:41:15
 * @version 1.0.0
 */
@AllArgsConstructor
public class AesPrivateKey implements Key {
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
