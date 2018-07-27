package com.ump.commons.encryption;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

import com.ump.commons.exception.CommonException;

public class DesEncryptor implements IEncryptor {

	@Override
	public byte[] encrypt(byte[] src, byte[] key) throws CommonException {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			String strKey = "1234567890125678890345678909";
			String plainText = "aaaaa";
			Key deskey = keyFactory.generateSecret(new DESedeKeySpec(strKey.getBytes("UTF8")));

			//String iv = "01234567";

			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			byte[] ivb = new byte[8];
			SecureRandom sr = new SecureRandom();
			sr.nextBytes(ivb);
			IvParameterSpec ips = new IvParameterSpec(ivb);
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			return cipher.doFinal(plainText.getBytes("UTF-8"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public byte[] decrypt(byte[] message, byte[] key) throws CommonException {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			String strKey = "1234567890125678890345678909";
			String plainText = "nADO0725QmQ=";

			Key deskey = keyFactory.generateSecret(new DESedeKeySpec(strKey.getBytes("UTF8")));

			String iv = "01234567";

			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");

			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			return cipher.doFinal(Base64.decodeBase64(plainText));
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		IEncryptor s = new DesEncryptor();
//		List result = Collections.EMPTY_LIST;
		String src = "aaaaaa";
		System.out.println(Base64.encodeBase64String(s.encrypt(src.getBytes("UTF8"), null)));
		//System.out.println(new String(s.decrypt(null, null)));
	}

	@Override
	public String encrypt(String plaintext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String ciphertext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encrypt(String key, String plaintext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String key, String ciphertext) {
		// TODO Auto-generated method stub
		return null;
	}
}
