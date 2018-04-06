package com.ump.commons;

import java.util.Locale;
import java.util.ResourceBundle;

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

	public static ResourceBundle getLocalResourceBundle(String resFile) {
		Locale myLocale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle(resFile, myLocale);
		return bundle;
	}

	public static String getErrorMsg(String errorCode) {
		try {
			return getLocalResourceBundle("config.messages.errorMsg").getString(errorCode);
		} catch (Exception e) {
			return "";
		}
	}
}
