package com.ump.uias;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
//@EnableEurekaClient
@ServletComponentScan
@EnableCaching // 开启缓存
//@EnableTransactionManagement
@ComponentScan({"com.ump.core","com.ump.uias","com.baomidou.mybatisplus.extension.service"})
@MapperScan(basePackages = { "com.ump.**.mapper","com.baomidou.**.mapper" })
@EnableWebMvc
@EnableSwagger2
public class UiasApplication {
	private static Logger logger = LoggerFactory.getLogger(UiasApplication.class);

	public static void main(String[] args) {
		logger.info("UIAS APP start...");
		SpringApplication.run(UiasApplication.class, args);
	}
}
