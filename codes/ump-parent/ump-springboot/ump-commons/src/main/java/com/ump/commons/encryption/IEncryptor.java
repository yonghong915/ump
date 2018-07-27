package com.ump.commons.encryption;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 
 * @author fangyh
 *
 */
public interface IEncryptor {
	/**
	 * 
	 */
	static final Charset ENCODING = StandardCharsets.UTF_8;

	public byte[] encrypt(byte[] key, byte[] plaintext);

	public String encrypt(String key, String plaintext);

	public String encrypt(String plaintext);

	public byte[] decrypt(byte[] key, byte[] ciphertext);

	public String decrypt(String key, String ciphertext);

	public String decrypt(String ciphertext);

}
