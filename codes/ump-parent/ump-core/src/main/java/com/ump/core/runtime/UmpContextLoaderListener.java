package com.ump.core.runtime;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

public class UmpContextLoaderListener extends ContextLoaderListener {
    Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("contextInitialized");
		super.contextInitialized(event);
	}
   
}
