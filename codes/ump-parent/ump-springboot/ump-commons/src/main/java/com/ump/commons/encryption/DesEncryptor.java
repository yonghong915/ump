package com.ump.commons.encryption;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.ump.commons.encryption.type.EncryptAlgorithm;
import com.ump.commons.encryption.type.ModeType;
import com.ump.commons.encryption.type.PaddingMode;

public class DesEncryptor extends AbstractEncryptor {

	public Cipher getCipher(ModeType modeType) throws NoSuchAlgorithmException, NoSuchPaddingException {
		return getCipher(EncryptAlgorithm.DES, modeType, PaddingMode.PKCS5Padding);
	}

	public Key generateKey(ModeType modeType, byte[] key)
			throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
		return generateKey(new DESKeySpec(key), EncryptAlgorithm.DES);
	}

	@Override
	public String encrypt(String key, String plaintext) {
		byte[] ciphertext = encrypt(ModeType.CBC, key.getBytes(ENCODING), plaintext.getBytes(ENCODING));
		return Base64.encodeBase64String(ciphertext);
	}

	@Override
	public String decrypt(String key, String ciphertext) {
		byte[] cipherByts = Base64.decodeBase64(ciphertext);
		byte[] plaintext = decrypt(ModeType.CBC, key.getBytes(ENCODING), cipherByts);
		return new String(plaintext, ENCODING);
	}
}
