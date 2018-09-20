package com.ump.core.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.google.common.collect.Lists;
import com.ump.core.interceptor.TokenInterceptor;

/**
 * 
 * @author fangyh
 * @date 2018-09-19 14:27:19
 * @version 1.0.0
 */
@Configuration
@ComponentScan(useDefaultFilters = true)
public class WebAppConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		List<String> patterns = Lists.newArrayList();
		registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**").addPathPatterns(patterns);
	}

}
