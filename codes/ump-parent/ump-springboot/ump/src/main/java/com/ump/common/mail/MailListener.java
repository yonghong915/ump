/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.common.mail;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-01 10:14:13
 *
 */
@Component
public class MailListener {
	@Async("asyncServiceExecutor")
	@Order
	@EventListener(MailEvent.class)
	public void saveSysLog(MailEvent event) {
		//MailInfo mailInfo = (MailInfo) event.getSource();
		// 保存日志
	}
}
