package com.ump.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * API
 *
 */
@SpringBootApplication
public class ApiApplication {
	private static Logger logger = LoggerFactory.getLogger(ApiApplication.class);

	public static void main(String[] args) {
		logger.info("API APP start...");
		SpringApplication.run(ApiApplication.class, args);
	}
}
