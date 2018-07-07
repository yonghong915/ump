package com.ump.sys.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
	private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	@Autowired
	private DataSourceProperties props;

	@Bean(name = "primaryDataSource")
	@Primary
	public DataSource primaryDataSource() {
		logger.info("数据库连接池创建中.......");
		HikariDataSource ds = new HikariDataSource();// DataSourceBuilder.create().build();
		ds.setDriverClassName(props.getDriverClassName());
		ds.setJdbcUrl(props.getJdbcUrl());
		ds.setUsername(props.getUsername());
		ds.setPassword(props.getPassword());
		return ds;
	}
}
