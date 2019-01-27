package com.ump.commons.encryption.des;

import com.ump.commons.encryption.AbstractEncryptor;
import com.ump.commons.encryption.internals.IEncryptor;
import com.ump.commons.encryption.internals.Key;
import com.ump.commons.encryption.internals.Opts;

public class DesEncryptor extends AbstractEncryptor implements IEncryptor {

	@Override
	public Key generateKey(Opts opts) {
		return new DesPrivateKey(genKey(opts).getEncoded(), false);
	}

	@Override
	public byte[] encrypt(Key key, byte[] plaintext, Opts opts) {
		DesPrivateKey privKey = (DesPrivateKey) key;
		return encrypt(privKey.getPrivKey(), plaintext, opts);
	}

	@Override
	public byte[] decrypt(Key key, byte[] ciphertext, Opts opts) {
		DesPrivateKey privKey = (DesPrivateKey) key;
		return decrypt(privKey.getPrivKey(), ciphertext, opts);
	}

}
