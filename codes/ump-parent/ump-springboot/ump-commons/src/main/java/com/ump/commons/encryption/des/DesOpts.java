package com.ump.commons.encryption.des;

import com.ump.commons.encryption.internals.Opts;
import com.ump.commons.encryption.type.EncryptAlgorithm;
import com.ump.commons.encryption.type.KeySizeEnum;
import com.ump.commons.encryption.type.ModeType;
import com.ump.commons.encryption.type.PaddingMode;

public class DesOpts implements Opts {

	@Override
	public String getAlgorithm() {
		return EncryptAlgorithm.DES.name();
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
