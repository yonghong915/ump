package com.ump.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-07-27 10:56:05
 */
//禁用数据源默认自动配置
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan({ "com.ump.core" })
@MapperScan(basePackages = { "com.ump.**.mapper" })
public class SpringCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringCoreApplication.class, args);
	}
}
