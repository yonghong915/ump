package com.ump.commons.encryption;
/**
 * 
 * @author fangyh
 *
 */

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.commons.exception.CommonException;
import com.ump.commons.web.StatusCode;

public class ThreeDesEncryptor implements IEncryptor {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String THREE_DES_KEY = "1234567890125678890345678909";

	public ThreeDesEncryptor() {
	}

	// public byte[] encrypt(SecretKey key, byte[] src) throws
	// NoSuchAlgorithmException, NoSuchPaddingException,
	// IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
	//
	// SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
	// String strKey = "1234567890125678890345678909";
	// String plainText = "aaaaa";
	// Key deskey = keyFactory.generateSecret(new
	// DESedeKeySpec(strKey.getBytes(ENCODING)));
	// Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
	// byte[] ivb = new byte[8];
	// SecureRandom sr = new SecureRandom();
	// sr.nextBytes(ivb);
	// IvParameterSpec ips = new IvParameterSpec(ivb);
	// cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
	// return cipher.doFinal(plainText.getBytes(ENCODING));
	// }
	//
	// public byte[] decrypt(SecretKey key, byte[] crypt) throws
	// NoSuchAlgorithmException, NoSuchPaddingException,
	// InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
	// Cipher cipher = Cipher.getInstance(DES_EDE);
	// cipher.init(Cipher.DECRYPT_MODE, key);
	// return cipher.doFinal(crypt);
	// }
	//
	// public static SecretKey genDESKey(String keyStr) {
	// byte[] keys = keyStr.getBytes(ENCODING);// 3DES 24 bytes key
	// return new SecretKeySpec(keys, DES_EDE);
	// }

	@Override
	public byte[] encrypt(byte[] key, byte[] plaintext) {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DigestAlgorithms.THREE_DES);
			Key deskey = keyFactory.generateSecret(new DESedeKeySpec(key));

			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");// DESede/CBC/PKCS5Padding
			byte[] keyIv = new byte[8];
			SecureRandom sr = new SecureRandom();
			sr.nextBytes(keyIv);
			IvParameterSpec ips = new IvParameterSpec(keyIv);
			cipher.init(Cipher.ENCRYPT_MODE, deskey, sr);
			return cipher.doFinal(plaintext);
		} catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			logger.error("ThreeDes Encrypt error:{}", e);
			throw new CommonException(StatusCode.ENCRYPT_FAIL);
		}

	}

	@Override
	public byte[] decrypt(byte[] key, byte[] ciphertext) {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DigestAlgorithms.THREE_DES);
			Key deskey = keyFactory.generateSecret(new DESedeKeySpec(key));
			byte[] keyIv = new byte[8];
			SecureRandom sr = new SecureRandom();
			sr.nextBytes(keyIv);
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(keyIv);
			cipher.init(Cipher.DECRYPT_MODE, deskey, sr);
			return cipher.doFinal(ciphertext);
		} catch (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			logger.error("ThreeDes Encrypt error:{}", e);
			throw new CommonException(StatusCode.DECRYPT_FAIL);
		}
	}

	public String encrypt(String key, String plaintext) {
		return Base64.encodeBase64String(encrypt(key.getBytes(ENCODING), plaintext.getBytes(ENCODING)));
	}

	@Override
	public String encrypt(String plaintext) {
		return encrypt(THREE_DES_KEY, plaintext);
	}

	public String decrypt(String key, String ciphertext) {
		return new String(decrypt(key.getBytes(ENCODING), Base64.decodeBase64(ciphertext)), ENCODING);
	}

	@Override
	public String decrypt(String ciphertext) {
		return decrypt(THREE_DES_KEY, ciphertext);
	}

	public static void main(String[] args) {
		ThreeDesEncryptor t = new ThreeDesEncryptor();
		byte[] en = t.encrypt(THREE_DES_KEY.getBytes(ENCODING), "aaaaa".getBytes(ENCODING));
		System.out.println(en);
		System.out.println(Base64.encodeBase64String(en));
		System.out.println(new String(t.decrypt(THREE_DES_KEY.getBytes(ENCODING), en)));
		// DigestUtils
		// System.out.println(en);
		// System.out.println(Base64.encodeBase64String(en));
		// System.out.println(new String(t.decrypt(THREE_DES_KEY.getBytes(ENCODING),
		// en)));

	}
}
