/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ump.interceptor.mybatis.MybatisInterceptor;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 19:40:07
 *
 */
@Configuration
public class MybatisInterceptorConfig {
	@Autowired
	MybatisInterceptor mybatisInterceptor;

	@Bean
	public String myInterceptor(SqlSessionFactory sqlSessionFactory) {
		sqlSessionFactory.getConfiguration().addInterceptor(mybatisInterceptor);
		return "interceptor";
	}
}
