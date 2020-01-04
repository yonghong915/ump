package com.ump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UmpApplication {
	public static void main(String[] args) {
		SpringApplication.run(UmpApplication.class, args);
	}
}
