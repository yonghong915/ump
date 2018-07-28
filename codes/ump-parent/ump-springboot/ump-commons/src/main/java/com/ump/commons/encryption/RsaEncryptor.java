package com.ump.commons.encryption;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

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

public class RsaEncryptor extends AbstractEncryptor {
	public static final String KEY_ALGORITHM_RSA = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	private static final String PUBLIC_KEY = "RSAPublicKey";

	private static final String PRIVATE_KEY = "RSAPrivateKey";

	/** */
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/** */
	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	public Cipher getCipher(ModeType modeType) throws NoSuchAlgorithmException, NoSuchPaddingException {
		return getCipher(EncryptAlgorithm.RSA, modeType, PaddingMode.PKCS1Padding);
	}

	/// RSA/ECB/PKCS1Padding
	// 生成秘钥对
	public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EncryptAlgorithm.RSA.name());
		SecureRandom secureRandom = new SecureRandom();
		keyPairGenerator.initialize(RSA_KEY_SIZE, secureRandom);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		return keyPair;
	}

	public static Map<String, Object> genKeyPairs() throws NoSuchAlgorithmException {
		KeyPair keyPair = getKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	public static byte[] sign(byte[] privateKey, byte[] data) throws Exception {
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
		KeyFactory keyFactory = KeyFactory.getInstance(EncryptAlgorithm.RSA.name());
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return signature.sign();
	}

	public static boolean verify(byte[] publicKey, byte[] data, byte[] sign) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance(EncryptAlgorithm.RSA.name());
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(sign);
	}

	public static void main(String[] args) {
		try {
			RsaEncryptor t = new RsaEncryptor();
			KeyPair keyPair = getKeyPair();
			byte[] pubKey = getPublicKey(keyPair);
			byte[] priKey = getPrivateKey(keyPair);
			byte[] data = "12345678567890填太个LlXkB"
					.getBytes(ENCODING);
			byte[] sign = sign(priKey, data);
			boolean verFlag = verify(pubKey, data, sign);
			System.out.println("verFlag=" + verFlag);

			byte[] encryedtxt = t.encrypt(data, string2PublicKey(pubKey));
			System.out.println(Base64.encodeBase64String(encryedtxt));

			byte[] srctext = t.decrypt(encryedtxt, string2PrivateKey(priKey));
			System.out.println(new String(srctext));

			System.out.println(t.encrypt("aaaa", new String(data)));
			// byte[] encrytxt = encryptByPublicKey(data, pubKey);
			// System.out.println(Base64.encodeBase64String(encrytxt));

			// byte[] srctst = decryptByPrivateKey(encrytxt, priKey);
			// System.out.println(new String(srctst));

			// Cipher cipher = Cipher.getInstance("RSA");//
			// java默认"RSA"="RSA/ECB/PKCS1Padding"
			// PublicKey publicKey = keyPair.getPublic();
			// cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			// byte[] endata = cipher.doFinal(data);
			// System.out.println(Base64.encodeBase64String(endata));
			//
			// PrivateKey privateKey = keyPair.getPrivate();
			// cipher.init(Cipher.DECRYPT_MODE, privateKey);
			// byte[] dedata = cipher.doFinal(endata);
			// System.out.println(new String(dedata));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取公钥(Base64编码)
	public static byte[] getPublicKey(KeyPair keyPair) {
		PublicKey publicKey = keyPair.getPublic();
		byte[] bytes = publicKey.getEncoded();
		return bytes;
	}

	// 获取私钥(Base64编码)
	public static byte[] getPrivateKey(KeyPair keyPair) {
		PrivateKey privateKey = keyPair.getPrivate();
		byte[] bytes = privateKey.getEncoded();
		return bytes;
	}

	// 将Base64编码后的公钥转换成PublicKey对象
	public static PublicKey string2PublicKey(byte[] pubKey) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubKey);
		KeyFactory keyFactory = KeyFactory.getInstance(EncryptAlgorithm.RSA.name());
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	// 将Base64编码后的私钥转换成PrivateKey对象
	public static PrivateKey string2PrivateKey(byte[] priKey) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(priKey);
		KeyFactory keyFactory = KeyFactory.getInstance(EncryptAlgorithm.RSA.name());
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	// 公钥加密
	public byte[] encrypt(byte[] data, PublicKey publicKey) throws Exception {
		Cipher cipher = getCipher(ModeType.ECB);// Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		// int blockSize = cipher.getBlockSize();
		// if (blockSize > 0) {
		// int outputSize = cipher.getOutputSize(data.length);
		// int leavedSize = data.length % blockSize;
		// int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length
		// / blockSize;
		// byte[] raw = new byte[outputSize * blocksSize];
		// int i = 0, remainSize = 0;
		// while ((remainSize = data.length - i * blockSize) > 0) {
		// int inputLen = remainSize > blockSize ? blockSize : remainSize;
		// cipher.doFinal(data, i * blockSize, inputLen, raw, i * outputSize);
		// i++;
		// }
		// return raw;
		// }
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	// 私钥解密
	public byte[] decrypt(byte[] encryptedData, PrivateKey privateKey) throws Exception {
		Cipher cipher = getCipher(ModeType.ECB);// Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;

	}

	public Key generateKey(ModeType modeType, byte[] key)
			throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
		KeyPair keyPair = getKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		return publicKey;
	}

	@Override
	public String encrypt(String key, String plaintext) throws CommonException {

		byte[] ciphertext = encrypt(ModeType.ECB, key.getBytes(ENCODING), plaintext.getBytes(ENCODING));
		return Base64.encodeBase64String(ciphertext);
	}

	@Override
	public String decrypt(String key, String ciphertext) throws CommonException {
		if (null == key || key.length() < 24) {
			throw new CommonException(StatusCode.KEY_LENGTH_INSUFFICIENT);
		}
		byte[] cipherByts = Base64.decodeBase64(ciphertext);
		byte[] plaintext = decrypt(ModeType.GCM, key.getBytes(ENCODING), cipherByts);
		return new String(plaintext, ENCODING);
	}

	/**
	 * 私钥解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            私钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

		// 生成私钥
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		// int blockSize = cipher.getBlockSize();
		// if (blockSize > 0) {
		// ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
		// int j = 0;
		// while (data.length - j * blockSize > 0) {
		// bout.write(cipher.doFinal(data, j * blockSize, blockSize));
		// j++;
		// }
		// return bout.toByteArray();
		// }
		return cipher.doFinal(data);
	}

	/**
	 * 公钥解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            公钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

		// 生成公钥
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/**
	 * 公钥加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            公钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);

		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		// int blockSize = cipher.getBlockSize();
		// if (blockSize > 0) {
		// int outputSize = cipher.getOutputSize(data.length);
		// int leavedSize = data.length % blockSize;
		// int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length
		// / blockSize;
		// byte[] raw = new byte[outputSize * blocksSize];
		// int i = 0, remainSize = 0;
		// while ((remainSize = data.length - i * blockSize) > 0) {
		// int inputLen = remainSize > blockSize ? blockSize : remainSize;
		// cipher.doFinal(data, i * blockSize, inputLen, raw, i * outputSize);
		// i++;
		// }
		// return raw;
		// }
		return cipher.doFinal(data);
	}

	/**
	 * 私钥加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            私钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {

		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);

		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);

		// 生成私钥
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		int blockSize = cipher.getBlockSize();
		if (blockSize > 0) {
			int outputSize = cipher.getOutputSize(data.length);
			int leavedSize = data.length % blockSize;
			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0, remainSize = 0;
			while ((remainSize = data.length - i * blockSize) > 0) {
				int inputLen = remainSize > blockSize ? blockSize : remainSize;
				cipher.doFinal(data, i * blockSize, inputLen, raw, i * outputSize);
				i++;
			}
			return raw;
		}
		return cipher.doFinal(data);
	}
}
