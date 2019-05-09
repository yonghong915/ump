package com.ump.pcms;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * PCMS微服务入口
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan
@MapperScan(basePackages = { "com.ump.pcms.mapper" })
public class PcmsApplication {
	private static Logger logger = LoggerFactory.getLogger(PcmsApplication.class);

	public static void main(String[] args) {
		logger.info("PCMS APP start...");
		SpringApplication.run(PcmsApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
