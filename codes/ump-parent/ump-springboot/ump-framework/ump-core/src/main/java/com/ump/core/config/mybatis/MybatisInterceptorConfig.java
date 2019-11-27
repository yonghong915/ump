package com.ump.core.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ump.core.interceptor.mybatis.MybatisInterceptor;

//@Configuration
public class MybatisInterceptorConfig {

	@Autowired
	MybatisInterceptor mybatisInterceptor;

	@Bean
	public String myInterceptor(SqlSessionFactory sqlSessionFactory) {
		//sqlSessionFactory.getConfiguration().addInterceptor(mybatisInterceptor);
		return "interceptor";
	}
}
