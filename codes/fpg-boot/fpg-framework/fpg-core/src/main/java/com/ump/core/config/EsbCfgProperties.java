package com.ump.core.config;

import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
public class EsbCfgProperties {

	@Setter
	@Getter
	private String esbIp;

	@Setter
	@Getter
	private int esbPort;

	@Setter
	@Getter
	private int esbTimeout;

	@Getter
	@Setter
	private int connTimeout;
}
