package com.fpg.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 * @author fangyh
 * @version 1.0.0
 * @date 2020-08-04
 * @since 1.0.0
 */
@SpringBootApplication
@EnableEurekaClient
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
