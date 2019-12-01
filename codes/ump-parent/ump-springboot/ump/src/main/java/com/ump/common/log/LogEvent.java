/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.common.log;

import org.springframework.context.ApplicationEvent;

import com.ump.entity.sys.LogEntity;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-30 19:00:22
 *
 */
public class LogEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3829069871677465995L;

	public LogEvent(LogEntity source) {
		super(source);
	}
}