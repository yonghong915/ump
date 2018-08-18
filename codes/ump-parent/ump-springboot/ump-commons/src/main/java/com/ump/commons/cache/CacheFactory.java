package com.ump.commons.cache;

public class CacheFactory {
	private static CacheFactory instance = null;

	private CacheFactory() {
	}

	public static synchronized CacheFactory getInstance() {
		if (null == instance) {
			instance = new CacheFactory();
		}
		return instance;
	}

	public ICache findCache(String cacheName) {
		return null;
	}
}
