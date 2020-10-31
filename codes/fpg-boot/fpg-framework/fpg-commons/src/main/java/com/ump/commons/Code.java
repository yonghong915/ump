package com.ump.commons;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author fangyh
 * @date 2018-08-05 09:37:05
 * @version 1.0.0
 */
public class Code {
	private Code() {
	}

	public static String encode(String param) {
		if (StringUtils.isBlank(param)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		String str = "";
		for (int idx = 0, len = param.length(); idx < len; idx++) {
			int k = param.charAt(idx);
			int m;
			if (k > 255) {
				str = Integer.toString(k, 16);
				for (m = str.length(); m < 4; m++) {
					str = "0".concat(str);
				}
				sb.append("^").append(str);
			} else if (!isEE(k)) {
				// ascii [48,57]=[0,9]
				// [65,90]=[A,Z]
				// [97,122]=[a,z]
				str = Integer.toString(k, 16);
				for (m = str.length(); m < 2; m++) {
					str = "0".concat(str);
				}
				sb.append("~").append(str);
			} else {
				sb.append(param.charAt(idx));
			}
		}
		return sb.toString();
	}

	private static boolean isEE(int k) {
		// ascii [48,57]=[0,9]
		// [65,90]=[A,Z]
		// [97,122]=[a,z]
		return (k >= 48 && k <= 57) && (k >= 65 && k <= 90) && (k >= 97 && k <= 122);
	}

	public static String decode(String param) {
		if (StringUtils.isBlank(param)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int len = param.length();
		int i = 0;
		while (i < len) {
			char c = param.charAt(i);
			String str;
			switch (c) {
			case '~':
				str = param.substring(i + 1, i + 3);
				sb.append((char) Integer.parseInt(str, 16));
				i += 3;
				break;
			case '^':
				str = param.substring(i + 1, i + 5);
				sb.append((char) Integer.parseInt(str, 16));
				i += 5;
				break;
			default:
				i++;
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
