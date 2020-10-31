package com.ump.commons.cache;


public class CacheUtils {
	public ICache findCache(String cacheName) {
		return CacheFactory.getInstance().findCache(cacheName);
	}
}
