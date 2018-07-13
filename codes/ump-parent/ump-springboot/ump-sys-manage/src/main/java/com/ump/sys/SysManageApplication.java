package com.ump.sys;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
// @EnableTransactionManagement
@EnableEurekaClient
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@RestController
public class SysManageApplication {
	public static void main(String[] args) {
		ApplicationContext appCtx = SpringApplication.run(SysManageApplication.class, args);
		// DataSource dataSource = appCtx.getBean(DataSource.class);
		// System.out.println("datasource is :" + dataSource);
		// // 检查数据库是否是hikar数据库连接池
		// if (!(dataSource instanceof HikariDataSource)) {
		// System.err.println(" Wrong datasource type :" +
		// dataSource.getClass().getCanonicalName());
		// System.exit(-1);
		// }
	}

	@RequestMapping("/hello")
	public String hello() {
		return "welcome.";
	}
}
