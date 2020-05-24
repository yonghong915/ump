package com.ump.commons.encryption.aes;

import com.ump.commons.encryption.AbstractEncryptor;
import com.ump.commons.encryption.internals.IEncryptor;
import com.ump.commons.encryption.internals.Key;
import com.ump.commons.encryption.internals.Opts;

/**
 * 
 * @author fangyh
 * @date 2018-08-15 22:40:15
 * @version 1.0.0
 */
public class AesEncryptor extends AbstractEncryptor implements IEncryptor {
	@Override
	public Key generateKey(Opts opts) {
		return new AesPrivateKey(genKey(opts).getEncoded(), false);
	}

	@Override
	public byte[] encrypt(Key key, byte[] plaintext, Opts opts) {
		AesPrivateKey privKey = (AesPrivateKey) key;
		return encrypt(privKey.getPrivKey(), plaintext, opts);
	}

	@Override
	public byte[] decrypt(Key key, byte[] ciphertext, Opts opts) {
		AesPrivateKey privKey = (AesPrivateKey) key;
		return decrypt(privKey.getPrivKey(), ciphertext, opts);
	}
}
