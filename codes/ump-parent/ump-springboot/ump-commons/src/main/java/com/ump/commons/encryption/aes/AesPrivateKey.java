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
	public byte[] Bytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] SKI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Symmetric() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Private() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Key PublicKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
