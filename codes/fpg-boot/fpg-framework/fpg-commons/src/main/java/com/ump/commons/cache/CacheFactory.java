package com.ump.commons.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheFactory {
	private Logger logger = LoggerFactory.getLogger(CacheFactory.class);
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
		logger.info("cacheName={}", cacheName);
		return null;
	}
}
