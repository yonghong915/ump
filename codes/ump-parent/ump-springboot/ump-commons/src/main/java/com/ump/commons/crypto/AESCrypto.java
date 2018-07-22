package com.ump.commons.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.exception.CommonException;

public class AESCrypto implements ICrypto {
	static Logger logger = LoggerFactory.getLogger(AESCrypto.class);

	@Override
	public byte[] encrypt(byte[] src, byte[] key) throws CommonException {
		//if (null == key || key.length != 32) {
		//	throw new IllegalArgumentException();
		//}

		// AES/CBC/PKCS5Padding

		try {
			String password = "123456";
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
			kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出128位的key生产者

			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null。

			Key key1 = new SecretKeySpec(enCodeFormat, "AES");

			/* 创建密码器 */
			Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

			/* 初始化为加密模式的密码器 */
			final byte [] iv = new byte [12]; 
			SecureRandom sr = new SecureRandom();
			sr.nextBytes(iv);
			cipher.init(Cipher.ENCRYPT_MODE, key1,new GCMParameterSpec(16 * Byte.SIZE, iv));
			//byte[] iv = cipher.getIV(); // See question #1
			//assert iv.length == 12; // See question #2

			// 加密
			byte[] cipherText = cipher.doFinal(src);
			assert cipherText.length == src.length + 16; // See question #3
			byte[] message = new byte[12 + src.length + 16]; // See question #4
			System.arraycopy(iv, 0, message, 0, 12);
			System.arraycopy(cipherText, 0, message, 12, cipherText.length);
			return message;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException | InvalidAlgorithmParameterException e) {
			throw new CommonException("aaaa", e);
		}

	}

	@Override
	public byte[] decrypt(byte[] message, byte[] key) throws CommonException {
		if (message.length < 12 + 16)
			throw new IllegalArgumentException();

		try {
			String password = "123456";
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
			Key key1 = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

			Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");// 创建密码器
			GCMParameterSpec params = new GCMParameterSpec(128, message, 0, 12);
			cipher.init(Cipher.DECRYPT_MODE, key1, params);// 初始化为解密模式的密码器
			return cipher.doFinal(message, 12, message.length - 12);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new CommonException("aaaa", e);
		}

	}

//	public static void main(String[] args) {
//		ICrypto cc = new AESCrypto();
//		String key = "8NONwyJtHesysWpM";
//		String src = "ABCDEFGH";
//		try {
//			String tee = Base64.encodeBase64String(cc.encrypt(src.getBytes("UTF-8"), key.getBytes("UTF-8")));
//			logger.info(tee);
//			String ret = new String(cc.decrypt(Base64.decodeBase64(tee), key.getBytes()));
//			logger.info("sege={}", ret);
//		} catch (Exception e) {
//			logger.error("error=", e);
//		}
//	}

}
