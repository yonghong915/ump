package com.ump.commons.encryption.des;

import com.ump.commons.encryption.internals.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class TripleDesPrivateKey implements Key {
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
	public boolean isSymmetric() {
		return true;
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
