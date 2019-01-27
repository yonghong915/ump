package com.ump.core.util;

import com.google.common.collect.ImmutableMap;
import com.ump.commons.web.RestStatus;
import com.ump.core.base.service.IEsbService;
import com.ump.core.batch.service.impl.ProductEsbService;

public enum ServiceMappingEnum implements RestStatus {
	SUCCESS("ClrgApl", ProductEsbService.class);
	private final String serviceName;

	private final Class className;

	ServiceMappingEnum(String serviceName, Class className) {
		this.serviceName = serviceName;
		this.className = className;
	}

	private static final ImmutableMap<String, ServiceMappingEnum> CACHE;
	static {
		final ImmutableMap.Builder<String, ServiceMappingEnum> builder = ImmutableMap.builder();
		for (ServiceMappingEnum statusCode : values()) {
			builder.put(statusCode.code(), statusCode);
		}
		CACHE = builder.build();
	}

	public static IEsbService getEsbServiceClass(String className) {
		final ServiceMappingEnum status = CACHE.get(className);
		if (status == null) {
			throw new IllegalArgumentException("No matching constant for [" + className + "]");
		}
		
		return null;
	}

	@Override
	public String code() {
		return serviceName;
	}

	@Override
	public String message() {
		return "";
	}
}
