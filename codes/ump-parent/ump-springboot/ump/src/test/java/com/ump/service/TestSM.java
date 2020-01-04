/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.service;

import java.security.KeyPair;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SM4;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-19 20:44:20
 *
 */
public class TestSM {

	public static void main(String[] args) {
		String data = "123456qwertyADG应用啊个个i";
		String encrytedSm3 = SmUtil.sm3(data);
		System.out.println("encrytedSm3=" + encrytedSm3);

		byte[] key = "1212345675656745".getBytes();
		SM4 sm4 = (SM4) SmUtil.sm4(key);
		String encryptedSm4 = sm4.encryptBase64(data);
		System.out.println("encryptedSm4=" + encryptedSm4);
		System.out.println(sm4.decryptStr(encryptedSm4));

		KeyPair keyPair = SecureUtil.generateKeyPair("SM2");
		byte[] privateKey = keyPair.getPrivate().getEncoded();
		byte[] publicKey = keyPair.getPublic().getEncoded();
		
		String priKeyStr = Base64.encode(privateKey);
		System.out.println("priKeyStr="+priKeyStr);
		
		String pubKeyStr = Base64.encode(publicKey);
		System.out.println("pubKeyStr="+pubKeyStr);
		
		//Base64.decode(priKeyStr)
		
		SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
		String encrypedSm2 = sm2.encryptBase64(data, KeyType.PublicKey);
		System.out.println("encrypedSm2=" + encrypedSm2);

		String decryptedSm2 = sm2.decryptStr(encrypedSm2, KeyType.PrivateKey);
		System.out.println("decryptedSm2=" + decryptedSm2);

		byte[] sign = sm2.sign(data.getBytes());
		boolean very = sm2.verify(data.getBytes(), sign);
		System.out.println("very=" + very);
	}

}
