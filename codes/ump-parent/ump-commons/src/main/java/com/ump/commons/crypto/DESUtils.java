package com.ump.commons.crypto;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class DESUtils {

	// 指定DES加密解密所用的密钥
	private static Key key;
	private static String KEY_STR = "mykey";

	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key = generator.generateKey();
			generator = null;
		} catch (NoSuchAlgorithmException e) {
			// log.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param plaintext
	 *            明文
	 * @return
	 */
	/* 对字符串进行DES加密，返回BASE64编码的加密字符串 */
	public static String encrypt(String plaintext) {

		try {
			byte[] src_byte = plaintext.getBytes("UTF-8");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(src_byte);
			return new String(Base64.encodeBase64(encryptStrBytes));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String encrypt(String key, String src) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(src)) {
			return null;
		}
		SecureRandom sr = getSecureRandom();
		DESKeySpec dks = new DESKeySpec(key.getBytes());

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(dks);

		Cipher cipher = getCipher();
		cipher.init(1, secretKey, sr);
		byte[] encryptedData = cipher.doFinal(src.getBytes());
		return new String(Base64.encodeBase64(encryptedData));
	}

	public static String decrypt(String key, String cipBys) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(cipBys)) {
			return null;
		}

		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key.getBytes());

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(dks);
		Cipher cipher = getCipher();
		cipher.init(2, secretKey, sr);

		// byte[] decrypt2str = new BASE64Decoder().decodeBuffer(cipBys);

		byte[] decrypt2str = Base64.decodeBase64(cipBys);
		byte[] decryptedData = cipher.doFinal(decrypt2str);
		return new String(decryptedData);
	}

	/**
	 * 
	 * @param ciphertext
	 *            密文
	 * @return
	 */
	/* 对BASE64编码的加密字符串进行解密，返回解密后的字符串 */
	public static String decrypt(String ciphertext) {

		try {
			// byte[] src_byte = base64de.decodeBuffer(ciphertext);
			byte[] src_byte = Base64.decodeBase64(ciphertext.getBytes());
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decrypt_byte = cipher.doFinal(src_byte);
			return new String(decrypt_byte, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static byte[] encode(byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		if (null == data || data.length < 1) {
			return data;
		}
		SecureRandom sr = new SecureRandom();
		byte[] rawKeyData = "LONGTOP.COM.CDWEI".getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(1, key, sr);
		byte[] encryptedData = cipher.doFinal(data);
		return encryptedData;
	}

	public static byte[] decode(byte[] encryptedData) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		if (null == encryptedData || encryptedData.length < 1) {
			return encryptedData;
		}

		SecureRandom sr = new SecureRandom();
		byte[] rawKeyData = "LONGTOP.COM.CDWEI".getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(2, key, sr);

		byte[] decryptedData = cipher.doFinal(encryptedData);
		return decryptedData;
	}

	private static SecureRandom getSecureRandom() {
		return new SecureRandom();
	}

	private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
		return Cipher.getInstance("DES");
	}

	public static void main(String[] args) {
		// try {
		// System.out.println(encode("webapp".getBytes()));
		System.out.println("root=" + encrypt("root"));
		System.out.println("root1234=" + encrypt("root1234"));
		// } catch (InvalidKeyException e) {
		// e.printStackTrace();
		// } catch (NoSuchAlgorithmException e) {
		// e.printStackTrace();
		// } catch (InvalidKeySpecException e) {
		// e.printStackTrace();
		// } catch (NoSuchPaddingException e) {
		// e.printStackTrace();
		// } catch (IllegalBlockSizeException e) {
		// e.printStackTrace();
		// } catch (BadPaddingException e) {
		// e.printStackTrace();
		// }
		// System.out.println(encrypt("webapp"));
		// System.out.println(decrypt("M7kK75KVqNw="));
		// File file = new File("D:/tt1.txt");
		// int i = 0;
		// do {
		// if (!file.exists()) {
		// try {
		// i++;
		// System.out.println("执行第" + i + "次");
		// Thread.sleep(30000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// } else {
		// break;
		// }
		// } while (i < 5);
		// if (i == 5) {
		// System.out.println("文件不存在");
		// return;
		// }
		// System.out.println("文件存在");
	}
}
