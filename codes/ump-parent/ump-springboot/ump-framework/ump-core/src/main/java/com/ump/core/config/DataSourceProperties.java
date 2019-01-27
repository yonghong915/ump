package com.ump.core.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Setter
	@Getter
	private String type;
	@Setter
	@Getter
	private String driverClassName;
	@Setter
	@Getter
	private String jdbcUrl;
	@Setter
	@Getter
	private String username;
	@Setter
	@Getter
	private String password;
}
