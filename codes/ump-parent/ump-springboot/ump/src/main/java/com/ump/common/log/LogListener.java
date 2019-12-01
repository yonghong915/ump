/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ump.entity.sys.LogEntity;
import com.ump.service.ILogService;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-30 19:03:11
 *
 */
@Component
public class LogListener {
	private Logger logger = LoggerFactory.getLogger(LogListener.class);
	@Autowired
	private ILogService logService;

	@Async("asyncServiceExecutor")
	@Order
	@EventListener(LogEvent.class)
	public void saveSysLog(LogEvent event) {
		LogEntity entity = (LogEntity) event.getSource();
		try {
			logService.save(entity);
		} catch (Exception e) {
			logger.error("An exception occurred in saving log.", e);
		}
	}
}
