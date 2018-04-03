package com.ump.cfn.shiro.service.impl;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("/resources/libs/**", "anon");
		hashMap.put("/resources/scripts/**", "anon");
		hashMap.put("/resources/styles/**", "anon");
		hashMap.put("/resources/images/**", "anon");
		hashMap.put("/login/login", "authc");
		hashMap.put("/**", "authc");
		return hashMap;
	}

}
