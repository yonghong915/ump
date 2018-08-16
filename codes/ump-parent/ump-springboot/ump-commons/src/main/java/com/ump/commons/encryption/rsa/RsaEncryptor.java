package com.ump.commons.encryption.rsa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

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

public class RsaEncryptor implements IEncryptor {
	static int RSA_KEY_SIZE = 1024;
	public static final String KEY_ALGORITHM_RSA = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	/**
	 * RSA最大加密明文大小
	 */
	protected static final int MAX_ENCRYPT_BLOCK = 117;

	/** */
	/**
	 * RSA最大解密密文大小
	 */
	protected static final int MAX_DECRYPT_BLOCK = 128;

	/// RSA/ECB/PKCS1Padding
	// 生成秘钥对
	public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EncryptAlgorithm.RSA.name());
		SecureRandom secureRandom = new SecureRandom();
		keyPairGenerator.initialize(RSA_KEY_SIZE, secureRandom);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		return keyPair;
	}

	@Override
	public Key generateKey(Opts opts) {
		KeyPair keyPair;
		try {
			keyPair = getKeyPair();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			return new RsaPrivateKey(privateKey.getEncoded(), publicKey.getEncoded(), false);
		} catch (NoSuchAlgorithmException e) {
			throw new CommonException(StatusCode.GENKEY_FAIL, e);
		}
	}

	@Override
	public byte[] encrypt(Key key, byte[] plaintext, Opts enOpts) {
		RsaPrivateKey priKey = (RsaPrivateKey) key;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(priKey.getPublicKey());
			KeyFactory keyFactory = KeyFactory.getInstance(EncryptAlgorithm.RSA.name());
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			int inputLen = plaintext.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(plaintext, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(plaintext, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException | IOException e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	@Override
	public byte[] decrypt(Key key, byte[] ciphertext, Opts deOpts) {
		RsaPrivateKey priKey = (RsaPrivateKey) key;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

			// 取得私钥
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey.getPrivKey());

			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

			// 生成私钥
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			int inputLen = ciphertext.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(ciphertext, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(ciphertext, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException | IOException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

}
