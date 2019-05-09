package com.ump.pcms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ump.core.ws.config.WsdlConfigManager;

@Component
@Order(1)
public class PcmsInitRunner implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(String... args) throws Exception {
		logger.info("The Runner start to initialize....");
		WsdlConfigManager.init();
	}

}
