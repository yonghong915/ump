package com.fpg.api.message;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Random;

/**
 * @author fangyh
 * @version 1.0.0
 * @date 2020-11-2
 * @since 1.0.0
 */
public class SecureUtil {
    private static final String BASE_CHAR_NUMBER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String SPLIT_STR = "!^";
    public static final int RANDOM_LEN = 16;

    private SecureUtil() {
        //do nothing
    }

    public static byte[] getAesKey() {
        return cn.hutool.crypto.SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
    }

    public static String encrypt4Aes(byte[] key, String content) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        return aes.encryptHex(content);
    }

    public static String decrypt4Aes(byte[] key, String content) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        return aes.decryptStr(content);
    }

    public static KeyPair getRsaKeyPair() {
        return cn.hutool.crypto.SecureUtil.generateKeyPair("RSA");
    }

    public static String getPublicKey4Rsa(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] pubEncBytes = publicKey.getEncoded();
        return Base64.encode(pubEncBytes);
    }

    public static String getPrivateKey4Rsa(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] priEncBytes = privateKey.getEncoded();
        return Base64.encode(priEncBytes);
    }

    public static String encrypt4Rsa(byte[] publicKey, String content) {
        RSA rsa = new RSA(null, publicKey);
        return rsa.encryptHex(StrUtil.bytes(content, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
    }

    public static String decrypt4Rsa(byte[] privateKey, String content) {
        RSA rsa = new RSA(privateKey, null);
        byte[] decrypt = rsa.decrypt(content, KeyType.PrivateKey);
        return new String(decrypt);
    }

    public static String getRandom(int length) {
        Random random = RandomUtil.getRandom(true);
        char[] chars = BASE_CHAR_NUMBER.toCharArray();
        char[] saltChars = new char[length];
        for (int i = 0; i < length; i++) {
            int n = random.nextInt(BASE_CHAR_NUMBER.length());
            saltChars[i] = chars[n];
        }
        return new String(saltChars);
    }
}
