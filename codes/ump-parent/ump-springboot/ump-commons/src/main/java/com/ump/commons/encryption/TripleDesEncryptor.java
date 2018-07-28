package com.ump.commons.encryption;
/**
 * 
 * @author fangyh
 *
 */

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.ump.commons.encryption.type.EncryptAlgorithm;
import com.ump.commons.encryption.type.ModeType;
import com.ump.commons.encryption.type.PaddingMode;
import com.ump.commons.exception.CommonException;
import com.ump.commons.web.StatusCode;

public class TripleDesEncryptor extends AbstractEncryptor {
	public Cipher getCipher(ModeType modeType) throws NoSuchAlgorithmException, NoSuchPaddingException {
		return getCipher(EncryptAlgorithm.DESede, modeType, PaddingMode.PKCS5Padding);
	}

	public Key generateKey(ModeType modeType, byte[] key)
			throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
		if (null == key || key.length < 24) {
			throw new CommonException(StatusCode.KEY_LENGTH_INSUFFICIENT);
		}
		return generateKey(new DESedeKeySpec(key), EncryptAlgorithm.DESede);
	}

	@Override
	public String encrypt(String key, String plaintext) throws CommonException {
		if (null == key || key.length() < 24) {
			throw new CommonException(StatusCode.KEY_LENGTH_INSUFFICIENT);
		}
		byte[] ciphertext = encrypt(ModeType.ECB, key.getBytes(ENCODING), plaintext.getBytes(ENCODING));
		return Base64.encodeBase64String(ciphertext);
	}

	@Override
	public String decrypt(String key, String ciphertext) throws CommonException {
		if (null == key || key.length() < 24) {
			throw new CommonException(StatusCode.KEY_LENGTH_INSUFFICIENT);
		}
		byte[] cipherByts = Base64.decodeBase64(ciphertext);
		byte[] plaintext = decrypt(ModeType.ECB, key.getBytes(ENCODING), cipherByts);
		return new String(plaintext, ENCODING);
	}
}
