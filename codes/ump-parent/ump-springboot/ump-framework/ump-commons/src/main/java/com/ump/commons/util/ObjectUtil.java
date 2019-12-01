/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.commons.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

import com.google.common.collect.Maps;
import com.google.gson.Gson;

import cn.hutool.core.util.StrUtil;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-01 21:00:28
 *
 */
public class ObjectUtil {
	public static String mapToString(Map<String, String[]> paramMap) {

		if (paramMap == null) {
			return "";
		}
		Map<String, Object> params = new HashMap<>(16);
		for (Map.Entry<String, String[]> param : paramMap.entrySet()) {

			String key = param.getKey();
			String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
			String obj = StrUtil.endWithIgnoreCase(param.getKey(), "password") ? "你是看不见我的" : paramValue;
			params.put(key, obj);
		}
		return new Gson().toJson(params);
	}

	public static String mapToStringAll(Map<String, String[]> paramMap) {

		if (paramMap == null) {
			return "";
		}
		Map<String, Object> params = new HashMap<>(16);
		for (Map.Entry<String, String[]> param : paramMap.entrySet()) {

			String key = param.getKey();
			String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
			params.put(key, paramValue);
		}
		return new Gson().toJson(params);
	}

	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map = Maps.newHashMap();
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				map.put(key + "", beanMap.get(key));
			}
		}
		return map;
	}
}
