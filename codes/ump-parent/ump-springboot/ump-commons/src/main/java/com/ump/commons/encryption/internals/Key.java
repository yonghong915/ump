package com.ump.commons.encryption.internals;

/**
 * 
 * @author fangyh
 * @date 2018-08-15 22:40:15
 * @version 1.0.0
 */
public interface Key {
	byte[] Bytes();

	// SKI returns the subject key identifier of this key.
	byte[] SKI();

	/**
	 * isSymmetric returns true if this key is a symmetric key, false is this key is
	 * asymmetric
	 * 
	 * @return
	 */
	boolean isSymmetric();

	// Private returns true if this key is a private key,
	// false otherwise.
	boolean Private();

	// PublicKey returns the corresponding public key part of an asymmetric
	// public/private key pair.
	// This method returns an error in symmetric key schemes.
	Key PublicKey();
}
