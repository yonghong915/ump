/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 09:16:27
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() throws IOException {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.ump")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() throws IOException {
		Contact contact = new Contact("","","") ;
		return new ApiInfoBuilder().title("誓言--------").termsOfServiceUrl("http://data.***.com/").contact(contact)
				.version("0.0.1").build();
	}
}
