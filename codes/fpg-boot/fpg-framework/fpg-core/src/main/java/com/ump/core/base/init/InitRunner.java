package com.ump.core.base.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class InitRunner implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(String... args) throws Exception {
		logger.info("The Runner start to initialize....");
		// 加载字典表
	}

}