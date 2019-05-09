package com.ump.pcms.esb;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ump.core.base.service.IEsbService;

@Component
public class EsbFactory {
	private static Logger logger = LoggerFactory.getLogger(EsbFactory.class);
	@Autowired
	private ApplicationContext applicationContext;
	private static ApplicationContext appCtx;

	@PostConstruct
	public void init() {
		appCtx = applicationContext;
	}

	public static IEsbService getNewEsbService(String serviceName) {
		logger.info("serviceName {}", serviceName);
		switch (serviceName) {
		case "PdLine":
			return appCtx.getBean(EsbPdLineService.class);
		default:
			return null;
		}
	}

}
