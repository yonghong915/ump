package com.fpg.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心应用，提供统一的配置信息
 * @author fangyh
 * @version 1.0.0
 * @date 2020-08-04
 * @since 1.0.0
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigApplication {
    public static void main(String[] args) {
    	SpringApplication.run(ConfigApplication.class, args);
    }
}
