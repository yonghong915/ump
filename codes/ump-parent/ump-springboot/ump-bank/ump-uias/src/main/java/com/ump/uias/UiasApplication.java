package com.ump.uias;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class UiasApplication {
	private static Logger logger = LoggerFactory.getLogger(UiasApplication.class);

	public static void main(String[] args) {
		logger.info("UIAS APP start...");
		SpringApplication.run(UiasApplication.class, args);
	}
}
