package com.ump.commons.util;

public final class ClassUtils {

	/**
	 * 
	 * @return
	 */
	public static ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (null == classLoader) {
			classLoader = ClassUtils.class.getClassLoader();
		}
		if (null == classLoader) {
			classLoader = ClassLoader.getSystemClassLoader();
		}
		return classLoader;
	}
}
