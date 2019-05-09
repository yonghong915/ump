package com.ump.core;

//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//禁用数据源默认自动配置
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
//@EnableBatchProcessing

public class SpringCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringCoreApplication.class, args);
	}
}
