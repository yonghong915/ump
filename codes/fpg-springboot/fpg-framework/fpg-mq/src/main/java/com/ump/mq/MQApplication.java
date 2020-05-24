package com.ump.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MQApplication {
	private static Logger logger = LoggerFactory.getLogger(MQApplication.class);

	public static void main(String[] args) {
		logger.info("MQApplication.....start");
		SpringApplication.run(MQApplication.class, args);
	}

}
