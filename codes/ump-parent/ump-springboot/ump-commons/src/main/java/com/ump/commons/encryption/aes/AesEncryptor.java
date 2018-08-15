package com.ump.commons.encryption.aes;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.ump.commons.encryption.internals.IEncryptor;
import com.ump.commons.encryption.internals.Key;
import com.ump.commons.encryption.internals.Opts;
import com.ump.commons.encryption.type.EncryptAlgorithm;
import com.ump.commons.exception.CommonException;
import com.ump.commons.web.StatusCode;
/**
 * 
 * @author fangyh
 * @date 2018-08-15 22:40:15
 * @version 1.0.0
 */
public class AesEncryptor implements IEncryptor {
	private static final String SEPARATOR = "/";

	public Key KeyGen(Opts opts) {
		javax.crypto.KeyGenerator kg;
		try {
			kg = javax.crypto.KeyGenerator.getInstance(EncryptAlgorithm.AES.name());
			kg.init(128, new SecureRandom());
			java.security.Key secretKey = kg.generateKey();
			java.security.Key key = new javax.crypto.spec.SecretKeySpec(secretKey.getEncoded(),
					EncryptAlgorithm.AES.name());

			AesPrivateKey priKey = new AesPrivateKey(key.getEncoded(), false);
			return priKey;
		} catch (NoSuchAlgorithmException e) {

		}
		return null;
	}

	@Override
	public byte[] Encrypt(Key key, byte[] plaintext, Opts opts) {
		try {
			SecureRandom secureRandom = new SecureRandom();
			Cipher cipher = Cipher
					.getInstance(opts.Algorithm() + SEPARATOR + opts.ModeType() + SEPARATOR + opts.PaddingMode());
			AesPrivateKey privKey = (AesPrivateKey) key;
			cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(privKey.getPrivKey(), "AES"),
					secureRandom);
			return cipher.doFinal(plaintext);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			// logger.error(StatusCode.ENCRYPT_FAIL.message(), e);
			System.out.println(e);
			throw new CommonException(StatusCode.ENCRYPT_FAIL);
		}
	}

	public byte[] Decrypt(Key key, byte[] ciphertext, Opts opts) {
		try {
			SecureRandom secureRandom = new SecureRandom();
			Cipher cipher = Cipher.getInstance("AES");
			AesPrivateKey privKey = (AesPrivateKey) key;
			cipher.init(Cipher.DECRYPT_MODE, new javax.crypto.spec.SecretKeySpec(privKey.getPrivKey(), "AES"),
					secureRandom);
			return cipher.doFinal(ciphertext);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			// logger.error(StatusCode.ENCRYPT_FAIL.message(), e);
			System.out.println(e);
			throw new CommonException(StatusCode.ENCRYPT_FAIL);
		}
	}
}
