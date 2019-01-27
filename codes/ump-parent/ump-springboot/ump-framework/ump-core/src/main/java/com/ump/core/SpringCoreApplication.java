package com.ump.core;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringCoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringCoreApplication.class, args);
	}
}
