package com.ump.commons.encryption.internals;

/**
 * 
 * @author fangyh
 * @date 2018-08-15 22:37:15
 * @version 1.0.0
 */
public interface Opts {
	/**
	 * 
	 * getAlgorithm returns specified encryption algorithm
	 * 
	 * @return
	 */
	String getAlgorithm();

	/**
	 * 
	 * @return
	 */
	String getModeType();

	/**
	 * 
	 * @return
	 */
	String getPaddingMode();

	int getKeySize();
}
