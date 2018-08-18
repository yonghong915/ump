package com.ump.core.util;

import com.ump.core.proxy.jdk.MyInvocationHandler;

public class ProxyUtil {
	private ProxyUtil() {
	}

	public static Object getProxyObj(Object targetObj) {
		MyInvocationHandler handler = new MyInvocationHandler(targetObj);
		return handler.getInstance();
	}
}
