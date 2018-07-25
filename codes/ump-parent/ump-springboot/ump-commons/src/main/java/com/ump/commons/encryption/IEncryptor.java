package com.ump.commons.encryption;

public interface IEncryptor {
	public byte[] encrypt(byte[] src, byte[] key);

	public byte[] decrypt(byte[] message, byte[] key);

	public String encrypt(String plaintext);

	public String decrypt(String ciphertext);
}
