package com.ump.commons.encryption.aes;

import com.ump.commons.encryption.internals.Opts;
import com.ump.commons.encryption.type.EncryptAlgorithm;
import com.ump.commons.encryption.type.KeySizeEnum;
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
	public String getAlgorithm() {
		return EncryptAlgorithm.AES.name();
	}

	@Override
	public String getModeType() {
		return ModeType.ECB.name();
	}

	@Override
	public String getPaddingMode() {
		return PaddingMode.PKCS5PADDING.name();
	}

	@Override
	public int getKeySize() {
		return KeySizeEnum.AES.value();
	}

}
