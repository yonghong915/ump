package com.ump.pcms.config;

import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import com.google.common.base.CaseFormat;

public class CustomWrapper extends MapWrapper {

	public CustomWrapper(MetaObject metaObject, Map<String, Object> map) {
		super(metaObject, map);
	}

	@Override
	public String findProperty(String name, boolean useCamelCaseMapping) {
		if (useCamelCaseMapping) {
			return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
		}
		return name;
	}

}
