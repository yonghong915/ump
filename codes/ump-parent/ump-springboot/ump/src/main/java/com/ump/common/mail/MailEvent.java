/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.common.mail;

import org.springframework.context.ApplicationEvent;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-01 10:12:24
 *
 */
public class MailEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5771905288178682491L;

	public MailEvent(MailInfo source) {
		super(source);
	}
}
