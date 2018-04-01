package com.ump.cfn.shiro;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("/resources/**", "anon");
		hashMap.put("/login/loginIndex", "anon");
		//hashMap.put("/login/login", "authc");
		hashMap.put("/**", "authc");
		return hashMap;
	}
	
}
