package com.ump.commons.encryption.internals;

/**
 * 
 * @author fangyh
 * @date 2018-08-15 22:37:15
 * @version 1.0.0
 */
public interface Opts {
	String Algorithm();

	String ModeType();

	String PaddingMode();
}
