package com.ump.commons.encryption;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.ump.commons.encryption.type.EncryptAlgorithm;
import com.ump.commons.encryption.type.ModeType;
import com.ump.commons.encryption.type.PaddingMode;
import com.ump.commons.exception.CommonException;
import com.ump.commons.web.StatusCode;

public class AesEncryptor extends AbstractEncryptor {
	public Cipher getCipher(ModeType modeType) throws NoSuchAlgorithmException, NoSuchPaddingException {
		return getCipher(EncryptAlgorithm.AES, modeType, PaddingMode.PKCS5Padding);
	}

	public Key generateKey(ModeType modeType, byte[] key)
			throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
		KeyGenerator kg = KeyGenerator.getInstance(EncryptAlgorithm.AES.name());
		kg.init(128, new SecureRandom(key));

		Key secretKey = kg.generateKey();
		return new SecretKeySpec(secretKey.getEncoded(), EncryptAlgorithm.AES.name());
	}

	@Override
	public String encrypt(String key, String plaintext) {

		byte[] ciphertext = encrypt(ModeType.GCM, key.getBytes(ENCODING), plaintext.getBytes(ENCODING));
		return Base64.encodeBase64String(ciphertext);
	}

	@Override
	public String decrypt(String key, String ciphertext) {
		if (null == key || key.length() < 24) {
			throw new CommonException(StatusCode.KEY_LENGTH_INSUFFICIENT);
		}
		byte[] cipherByts = Base64.decodeBase64(ciphertext);
		byte[] plaintext = decrypt(ModeType.GCM, key.getBytes(ENCODING), cipherByts);
		return new String(plaintext, ENCODING);
	}

}
