package com.ump.commons.encryption.internals;

/**
 * 
 * @author fangyh
 * @date 2018-08-15 22:40:15
 * @version 1.0.0
 */
public interface Key {
	/**
	 * isSymmetric returns true if this key is a symmetric key, false is this key is
	 * asymmetric
	 * 
	 * @return
	 */
	boolean isSymmetric();
}
