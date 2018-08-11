package com.ump.commons.encryption.aes;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import com.ump.commons.encryption.bccsp.EncrypterOpts;
import com.ump.commons.encryption.bccsp.Key;
import com.ump.commons.encryption.sw.internals.Encryptor;
import com.ump.commons.exception.CommonException;
import com.ump.commons.web.StatusCode;

public class AesEncryptor implements Encryptor {
	private static final String SEPARATOR = "/";

	@Override
	public byte[] Encrypt(Key key, byte[] plaintext, EncrypterOpts opts) {
		try {
			SecureRandom secureRandom = new SecureRandom();
			Cipher cipher = Cipher
					.getInstance(opts.Algorithm() + SEPARATOR + opts.ModeType() + SEPARATOR + opts.PaddingMode());
			AesPrivateKey privKey = (AesPrivateKey) key;
			cipher.init(Cipher.ENCRYPT_MODE, new javax.crypto.spec.SecretKeySpec(privKey.getPrivKey(), "AES"),
					secureRandom);
			return cipher.doFinal(plaintext);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			// logger.error(StatusCode.ENCRYPT_FAIL.message(), e);
			System.out.println(e);
			throw new CommonException(StatusCode.ENCRYPT_FAIL);
		}
	}
}
