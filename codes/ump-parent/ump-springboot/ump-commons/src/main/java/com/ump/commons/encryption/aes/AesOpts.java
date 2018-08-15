package com.ump.commons.encryption.aes;

import com.ump.commons.encryption.internals.Opts;
import com.ump.commons.encryption.type.EncryptAlgorithm;
import com.ump.commons.encryption.type.ModeType;
import com.ump.commons.encryption.type.PaddingMode;

/**
 * 
 * @author fangyh
 * @date 2018-08-15 22:40:15
 * @version 1.0.0
 */
public class AesOpts implements Opts {
	@Override
	public String Algorithm() {
		return EncryptAlgorithm.AES.name();
	}

	@Override
	public String ModeType() {
		return ModeType.ECB.name();
	}

	@Override
	public String PaddingMode() {
		return PaddingMode.PKCS5Padding.name();
	}

}
