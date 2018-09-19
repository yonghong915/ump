package com.ump.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.ump.core.interceptor.TokenInterceptor;

/**
 * 
 * @author fangyh
 * @date 2018-09-19 14:27:19
 * @version 1.0.0
 */
@Configuration
@ComponentScan(useDefaultFilters = true)
public class WebAppConfig extends WebMvcConfigurationSupport  {
	public void addInterceptor(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");
	}
}
