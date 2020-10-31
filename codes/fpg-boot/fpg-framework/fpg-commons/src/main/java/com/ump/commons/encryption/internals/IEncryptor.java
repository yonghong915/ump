package com.ump.commons.encryption.internals;

/**
 * 
 * @author fangyh
 * @date 2018-08-15 22:29:15
 * @version 1.0.0
 */
public interface IEncryptor {

	public Key generateKey(Opts opts);

	public byte[] encrypt(Key key, byte[] plaintext, Opts enOpts);

	public byte[] decrypt(Key key, byte[] ciphertext, Opts deOpts);

}
