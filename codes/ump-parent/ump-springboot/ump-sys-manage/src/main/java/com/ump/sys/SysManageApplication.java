package com.ump.sys;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class SysManageApplication {
	public static void main(String[] args) {
		ApplicationContext appCtx = SpringApplication.run(SysManageApplication.class, args);
		DataSource dataSource = appCtx.getBean(DataSource.class);
		System.out.println("datasource is :" + dataSource);
		// 检查数据库是否是hikar数据库连接池
		if (!(dataSource instanceof HikariDataSource)) {
			System.err.println(" Wrong datasource type :" + dataSource.getClass().getCanonicalName());
			System.exit(-1);
		}
	}
}
