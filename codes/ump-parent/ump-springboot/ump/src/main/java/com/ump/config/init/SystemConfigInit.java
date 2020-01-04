/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.config.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-21 10:48:15
 *
 */
@Component
@Order
public class SystemConfigInit implements CommandLineRunner, EnvironmentAware {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 在服务启动后执行，会在@Bean实例化之后执行
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.info("init ");
        
	}

	/*
	 * 在SystemConfigDao实例化之后、@Bean实例化之前执行 常用于读取数据库配置以供其它bean使用 environment对象可以获取配置文件的配置，也可以把配置设置到该对象中
	 */
	@Override
	public void setEnvironment(Environment environment) {
		logger.info("init config environment...");

	}

}
