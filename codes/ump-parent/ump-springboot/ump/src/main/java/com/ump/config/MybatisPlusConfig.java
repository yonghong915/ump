/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.config;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.pagehelper.PageHelper;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 09:13:32
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.ump.mapper*")
public class MybatisPlusConfig {
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("h2");
		return page;
	}

	// 配置mybatis的分页插件pageHelper
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("dialect", "h2"); // 配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}
