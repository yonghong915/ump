package com.ump.commons.encryption;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.commons.encryption.type.EncryptAlgorithm;
import com.ump.commons.encryption.type.ModeType;
import com.ump.commons.encryption.type.PaddingMode;
import com.ump.commons.exception.CommonException;
import com.ump.commons.web.StatusCode;

/**
 * 
 * @author fangyh
 * @date 2018-07-31 08:55:31
 * @version 1.0.0
 */
public abstract class AbstractEncryptor implements IEncryptor {
	private static Logger logger = LoggerFactory.getLogger(AbstractEncryptor.class);
	protected static int RSA_KEY_SIZE = 1024;
	/**
	 * 
	 */
	static final Charset ENCODING = StandardCharsets.UTF_8;
	private static final String SEPARATOR = "/";

	/**
	 * 
	 * @param modeType
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 */
	protected abstract Key generateKey(ModeType modeType, byte[] key)
			throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException;

	protected Key generateKey(KeySpec keySpec, EncryptAlgorithm encryptAlgorithm)
			throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(encryptAlgorithm.name());
		SecretKey secureKey = keyFactory.generateSecret(keySpec);
		return secureKey;
	}

	protected static Cipher getCipher(EncryptAlgorithm algorithm, ModeType modeType, PaddingMode paddingMode)
			throws NoSuchAlgorithmException, NoSuchPaddingException {
		String encryptAlgorithm = algorithm.name() + SEPARATOR + modeType.name() + SEPARATOR + paddingMode.name();
		return Cipher.getInstance(encryptAlgorithm);
	}

	protected abstract Cipher getCipher(ModeType modeType) throws NoSuchAlgorithmException, NoSuchPaddingException;

	protected byte[] encrypt(ModeType modeType, byte[] key, byte[] plaintext) throws CommonException {
		try {
			SecureRandom secureRandom = new SecureRandom();
			Key secureKey = generateKey(modeType, key);
			Cipher cipher = getCipher(modeType);
			if (ModeType.ECB.equals(modeType)) {
				cipher.init(Cipher.ENCRYPT_MODE, secureKey, secureRandom);
			} else if (ModeType.GCM.equals(modeType)) {
				GCMParameterSpec params = new GCMParameterSpec(128, key, 0, 12);
				cipher.init(Cipher.ENCRYPT_MODE, secureKey, params);//
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(key));
			}
			return cipher.doFinal(plaintext);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException
				| BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException e) {
			logger.error(StatusCode.ENCRYPT_FAIL.message(), e);
			System.out.println(e);
			throw new CommonException(StatusCode.ENCRYPT_FAIL);
		}
	}

	protected byte[] decrypt(ModeType modeType, byte[] key, byte[] ciphertext) throws CommonException {
		try {
			SecureRandom secureRandom = new SecureRandom();
			Key secureKey = generateKey(modeType, key);
			Cipher cipher = getCipher(modeType);
			if (ModeType.ECB.equals(modeType)) {
				cipher.init(Cipher.DECRYPT_MODE, secureKey, secureRandom);
			} else if (ModeType.GCM.equals(modeType)) {
				GCMParameterSpec params = new GCMParameterSpec(128, key, 0, 12);
				cipher.init(Cipher.DECRYPT_MODE, secureKey, params);//
			} else {
				cipher.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(key));
			}
			return cipher.doFinal(ciphertext);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException
				| BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException e) {
			logger.error(StatusCode.DECRYPT_FAIL.message(), e);
			throw new CommonException(StatusCode.DECRYPT_FAIL);
		}
	}

}
