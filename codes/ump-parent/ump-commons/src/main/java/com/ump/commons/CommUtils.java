package com.ump.commons;

public class CommUtils {
	public static boolean isEmpty(Object obj) {
		if (null == obj) {
			return true;
		}
		if (obj instanceof String) {
			return "".equals(obj);
		}
		return false;
	}
}
