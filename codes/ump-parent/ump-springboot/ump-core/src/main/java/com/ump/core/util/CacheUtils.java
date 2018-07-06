package com.ump.core.util;

import com.ump.core.cache.CacheFactory;
import com.ump.core.cache.ICache;

public class CacheUtils {
	public ICache findCache(String cacheName) {
		ICache cache = CacheFactory.getInstance().findCache(cacheName);
		return cache;
	}
}
