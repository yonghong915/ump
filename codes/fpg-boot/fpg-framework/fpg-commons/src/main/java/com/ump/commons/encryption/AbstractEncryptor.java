package com.ump.commons.encryption;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.commons.encryption.internals.Opts;
import com.ump.commons.exception.CommonException;
import com.ump.commons.web.StatusCode;

public abstract class AbstractEncryptor {
	protected static Logger logger = LoggerFactory.getLogger(AbstractEncryptor.class);
	protected static final Charset ENCODING = StandardCharsets.UTF_8;
	protected static final String SEPARATOR = "/";

	protected Key genKey(Opts opts) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(opts.getAlgorithm());
			keyGenerator.init(opts.getKeySize(), new SecureRandom());
			return keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			logger.error(StatusCode.GENKEY_FAIL.message(), e);
			throw new CommonException(StatusCode.GENKEY_FAIL);
		}
	}

	protected byte[] encrypt(byte[] key, byte[] plaintext, Opts opts) {
		try {
			SecureRandom secureRandom = new SecureRandom();
			Cipher cipher = Cipher.getInstance(
					opts.getAlgorithm() + SEPARATOR + opts.getModeType() + SEPARATOR + opts.getPaddingMode());
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, opts.getAlgorithm()), secureRandom);
			return cipher.doFinal(plaintext);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			logger.error(StatusCode.ENCRYPT_FAIL.message(), e);
			throw new CommonException(StatusCode.ENCRYPT_FAIL);
		}
	}

	protected byte[] decrypt(byte[] key, byte[] ciphertext, Opts opts) {
		try {
			SecureRandom secureRandom = new SecureRandom();
			Cipher cipher = Cipher.getInstance(
					opts.getAlgorithm() + SEPARATOR + opts.getModeType() + SEPARATOR + opts.getPaddingMode());
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, opts.getAlgorithm()), secureRandom);
			return cipher.doFinal(ciphertext);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			logger.error(StatusCode.ENCRYPT_FAIL.message(), e);
			throw new CommonException(StatusCode.ENCRYPT_FAIL);
		}
	}
}
