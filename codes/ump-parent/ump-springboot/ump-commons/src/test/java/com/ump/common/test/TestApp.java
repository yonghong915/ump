package com.ump.common.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ump.commons.encryption.AesEncryptor;
import com.ump.commons.encryption.DesEncryptor;
import com.ump.commons.encryption.IEncryptor;
import com.ump.commons.encryption.TripleDesEncryptor;

class TestApp {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	 @Test
	 void testDES() {
	 IEncryptor s = new DesEncryptor();
	 String plaintext = "aaaaaa大改动bbbbb";
	 String key = "123ffghg";
	 String ciphertext = s.encrypt(key, plaintext);
	 System.out.println(ciphertext);
	 String cipherRtn = s.decrypt(key, ciphertext);
	 System.out.println(s.decrypt(key, ciphertext));
	 assertEquals(plaintext, cipherRtn);
	 }

	@Test
	void testTripleDES() {
		IEncryptor s = new TripleDesEncryptor();
		String plaintext = "aaaaaa大改动bbbbb";
		String key = "sgeeetyuiopopopopopliopo";
		String ciphertext = s.encrypt(key, plaintext);
		System.out.println(ciphertext);
		String cipherRtn = s.decrypt(key, ciphertext);
		System.out.println(s.decrypt(key, ciphertext));
		assertEquals(plaintext, cipherRtn);
	}
	
	@Test
	void testTripleAES() {
		IEncryptor s = new AesEncryptor();
		String plaintext = "aaaaaa大改动bbbbb";
		String key = "sgeeetyuiopopopopopliopo";
		String ciphertext = s.encrypt(key, plaintext);
		System.out.println(ciphertext);
		String cipherRtn = s.decrypt(key, ciphertext);
		System.out.println(s.decrypt(key, ciphertext));
		assertEquals(plaintext, cipherRtn);
	}
}
