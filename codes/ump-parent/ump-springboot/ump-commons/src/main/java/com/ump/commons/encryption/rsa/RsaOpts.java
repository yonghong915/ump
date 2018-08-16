package com.ump.commons.encryption.rsa;

import com.ump.commons.encryption.internals.Opts;
import com.ump.commons.encryption.type.EncryptAlgorithm;
import com.ump.commons.encryption.type.KeySizeEnum;
import com.ump.commons.encryption.type.ModeType;
import com.ump.commons.encryption.type.PaddingMode;

public class RsaOpts implements Opts {

	@Override
	public String getAlgorithm() {
		return EncryptAlgorithm.RSA.name();
	}

	@Override
	public String getModeType() {
		return ModeType.ECB.name();
	}

	@Override
	public String getPaddingMode() {
		return PaddingMode.PKCS5Padding.name();
	}

	@Override
	public int getKeySize() {
		return KeySizeEnum.DES.value();
	}

}
