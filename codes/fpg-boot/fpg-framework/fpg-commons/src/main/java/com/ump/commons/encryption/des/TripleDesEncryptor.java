package com.ump.commons.encryption.des;

import com.ump.commons.encryption.AbstractEncryptor;
import com.ump.commons.encryption.internals.IEncryptor;
import com.ump.commons.encryption.internals.Key;
import com.ump.commons.encryption.internals.Opts;

/**
 * 
 * @version 1.0.0
 * @author fangyh key=112 168
 * @date 2018年7月31日 上午8:30:52
 */
public class TripleDesEncryptor extends AbstractEncryptor implements IEncryptor {
	@Override
	public Key generateKey(Opts opts) {
		return new TripleDesPrivateKey(genKey(opts).getEncoded(), false);
	}

	@Override
	public byte[] encrypt(Key key, byte[] plaintext, Opts opts) {
		TripleDesPrivateKey privKey = (TripleDesPrivateKey) key;
		return encrypt(privKey.getPrivKey(), plaintext, opts);
	}

	@Override
	public byte[] decrypt(Key key, byte[] ciphertext, Opts opts) {
		TripleDesPrivateKey privKey = (TripleDesPrivateKey) key;
		return decrypt(privKey.getPrivKey(), ciphertext, opts);
	}
}
