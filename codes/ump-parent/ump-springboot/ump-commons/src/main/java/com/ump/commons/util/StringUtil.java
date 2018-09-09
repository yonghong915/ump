package com.ump.commons.util;

import java.util.UUID;

public class StringUtil {
	private StringUtil() {
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
