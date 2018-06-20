package com.ump.commons;

public class Code {
	public static void main(String[] args) {
		System.out.println(encode("天台ee$#$%r3t"));
		System.out.println(decode("^5929^53f0ee~24~23~24~25r3t"));
	}

	public static String encode(String param) {
		if (null == param) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		String str = null;
		int len = param.length();
		for (int idx = 0; idx < len; idx++) {
			str = "";
			int k = param.charAt(idx);
			int m;
			if (k > 255) {
				str = Integer.toString(k, 16);
				for (m = str.length(); m < 4; m++) {
					str = "0".concat(str);
				}
				sb.append("^").append(str);
			} else if ((k < 48) || ((k > 57) && (k < 65)) || ((k > 90) && (k < 97)) || (k > 122)) {
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

	public static String decode(String param) {
		if (null == param) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		int len = param.length();
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			String str;
			switch (c) {
			case '~':
				str = param.substring(i + 1, i + 3);
				sb.append((char) Integer.parseInt(str, 16));
				i += 2;
				break;

			case '^':
				str = param.substring(i + 1, i + 5);
				sb.append((char) Integer.parseInt(str, 16));
				i += 4;
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
