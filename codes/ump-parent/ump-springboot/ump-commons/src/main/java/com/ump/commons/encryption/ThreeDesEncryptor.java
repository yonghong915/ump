package com.ump.commons.encryption;
/**
 * 
 * @author fangyh
 *
 */

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ThreeDesEncryptor implements IEncryptor {
	private static final Charset ENCODING = StandardCharsets.UTF_8;
	private static final String DES_EDE = "DESede";

	private ThreeDesEncryptor() {
	}

	public byte[] encrypt(SecretKey key, byte[] src) throws NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance(DES_EDE);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(src);
	}

	public byte[] decrypt(SecretKey key, byte[] crypt) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(DES_EDE);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(crypt);
	}

	public static SecretKey genDESKey(String keyStr) {
		byte[] keys = keyStr.getBytes(ENCODING);// 3DES 24 bytes key
		return new SecretKeySpec(keys, DES_EDE);
	}

	@Override
	public byte[] encrypt(byte[] src, byte[] key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] decrypt(byte[] message, byte[] key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encrypt(String plaintext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String ciphertext) {
		// TODO Auto-generated method stub
		return null;
	}
}
