package com.ump.commons.encryption.internals;

/**
 * 
 * @author fangyh
 * @date 2018-08-15 22:29:15
 * @version 1.0.0
 */
public interface IEncryptor {

	public Key KeyGen(Opts opts);

	public byte[] Encrypt(Key key, byte[] plaintext, Opts enOpts);

	public byte[] Decrypt(Key key, byte[] ciphertext, Opts deOpts);

}
