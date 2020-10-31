package com.ump.commons;

import java.lang.reflect.Array;
import java.util.Locale;
import java.util.ResourceBundle;

public class CommUtils {
	private CommUtils() {
	}

	public static boolean isEmpty(Object obj) {
		if (null == obj) {
			return true;
		}
		if (obj instanceof String) {
			return "".equals(obj);
		}
		if (obj instanceof Array) {
			return Array.getLength(obj) == 0;
		}
		return false;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	public static ResourceBundle getLocalResourceBundle(String resFile) {
		Locale myLocale = Locale.getDefault();
		return ResourceBundle.getBundle(resFile, myLocale);
	}

	public static String getErrorMsg(String errorCode) {
		try {
			return getLocalResourceBundle("config.messages.errorMsg").getString(errorCode);
		} catch (Exception e) {
			return "";
		}
	}
}
