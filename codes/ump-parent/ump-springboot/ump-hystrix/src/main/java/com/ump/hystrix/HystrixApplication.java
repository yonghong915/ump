package com.ump.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
// @EnableFeignClients
@EnableHystrix
// @EnableHystrixDashboard
@EnableCircuitBreaker
public class HystrixApplication {
	public static void main(String[] args) {
		SpringApplication.run(HystrixApplication.class, args);
	}
}
