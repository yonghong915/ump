package com.ump.commons.crypto;

import com.ump.exception.CommonException;

public interface ICrypto {
	public byte[] encrypt(byte[] src, byte[] key) throws CommonException;

	public byte[] decrypt(byte[] message, byte[] key) throws CommonException;
}
