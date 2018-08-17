package com.ump.common.test;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.commons.encryption.aes.AesEncryptor;
import com.ump.commons.encryption.aes.AesOpts;
import com.ump.commons.encryption.des.DesEncryptor;
import com.ump.commons.encryption.des.DesOpts;
import com.ump.commons.encryption.des.TripleDesEncryptor;
import com.ump.commons.encryption.des.TripleDesOpts;
import com.ump.commons.encryption.internals.IEncryptor;
import com.ump.commons.encryption.internals.Key;
import com.ump.commons.encryption.internals.Opts;
import com.ump.commons.encryption.rsa.RsaEncryptor;
import com.ump.commons.encryption.rsa.RsaOpts;

public class EncryptorTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testAesEncryptor() {
		IEncryptor encry = new AesEncryptor();
		Opts opts = new AesOpts();
		Key key = encry.generateKey(opts);
		String plaintext = "aA1~!@#$%^&*()_+`";

		byte[] ciphertext = encry.encrypt(key, plaintext.getBytes(), opts);
		logger.info("aes ciphertext={}", Base64.encodeBase64String(ciphertext));

		byte[] pltxt = encry.decrypt(key, ciphertext, opts);
		String decryptedText = new String(pltxt);
		logger.info("aes plaintext={}", decryptedText);
		Assert.assertEquals(plaintext, decryptedText);
	}

	@Test
	public void testDes() {
		IEncryptor encry = new DesEncryptor();
		Opts opts = new DesOpts();
		Key key = encry.generateKey(opts);
		String plaintext = "aA1~!@#$%^&*()_+`";

		byte[] ciphertext = encry.encrypt(key, plaintext.getBytes(), opts);
		logger.info("des ciphertext={}", Base64.encodeBase64String(ciphertext));

		byte[] pltxt = encry.decrypt(key, ciphertext, opts);
		String decryptedText = new String(pltxt);
		logger.info("des plaintext={}", decryptedText);
		Assert.assertEquals(plaintext, decryptedText);
	}

	@Test
	public void testTripleDes() {
		IEncryptor encry = new TripleDesEncryptor();
		Opts opts = new TripleDesOpts();
		Key key = encry.generateKey(opts);
		String plaintext = "aA1~!@#$%^&*()_+`";

		byte[] ciphertext = encry.encrypt(key, plaintext.getBytes(), opts);
		logger.info("tripleDes ciphertext={}", Base64.encodeBase64String(ciphertext));

		byte[] pltxt = encry.decrypt(key, ciphertext, opts);
		String decryptedText = new String(pltxt);
		logger.info("tripleDes plaintext={}", decryptedText);
		Assert.assertEquals(plaintext, decryptedText);
	}

	@Test
	public void testRsa() {
		IEncryptor encry = new RsaEncryptor();
		Opts opts = new RsaOpts();
		Key key = encry.generateKey(opts);
		String plaintext = "aA1~!@#$%^&*()_+`";

		byte[] ciphertext = encry.encrypt(key, plaintext.getBytes(), opts);
		logger.info("rsa ciphertext={}", Base64.encodeBase64String(ciphertext));

		byte[] pltxt = encry.decrypt(key, ciphertext, opts);
		String decryptedText = new String(pltxt);
		logger.info("rsa plaintext={}", decryptedText);
		Assert.assertEquals(plaintext, decryptedText);
	}
}
