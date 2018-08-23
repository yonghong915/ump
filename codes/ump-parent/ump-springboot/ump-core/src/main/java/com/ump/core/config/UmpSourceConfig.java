package com.ump.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource(value = "classpath:config/ump-app.properties", ignoreResourceNotFound = true)
public class UmpSourceConfig {

}
