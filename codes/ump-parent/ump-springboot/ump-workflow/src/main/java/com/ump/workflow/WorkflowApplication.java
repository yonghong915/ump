package com.ump.workflow;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class WorkflowApplication {
	public static void main(String[] args) {
		SpringApplication.run(WorkflowApplication.class, args);
	}

	@Value("${workname}") // git配置文件里的key
	String myww;

	@RequestMapping(value = "/hi")
	public String hi() {
		return myww;
	}
}
