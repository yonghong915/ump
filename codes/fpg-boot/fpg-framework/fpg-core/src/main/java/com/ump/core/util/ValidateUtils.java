/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-08-07 22:34:29
 *
 */
public class ValidateUtils {
	private ValidateUtils() {
	}

	public static boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}

	public static boolean isEmpty(Object value) {
		if (null == value) {
			return true;
		}
		if (value instanceof String) {
			if (((String) value).isEmpty()) {
				return true;
			}
		} else if (value instanceof Collection) {
			if (((Collection<?>) value).isEmpty()) {
				return true;
			}
		} else if (value instanceof Map) {
			if (((Map<?, ?>) value).isEmpty()) {
				return true;
			}
		}
		return false;
	}
}
