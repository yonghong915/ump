package com.ump.commons.encryption;

/**
 * 
 * @author fangyh
 *
 */
public interface IEncryptor {

	public String encrypt(String key, String plaintext);

	public String decrypt(String key, String ciphertext);

}
