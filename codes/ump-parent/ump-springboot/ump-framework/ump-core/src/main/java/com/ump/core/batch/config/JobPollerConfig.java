package com.ump.core.batch.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 调度器配置
 * 
 * @author fangyh
 *
 */
public class JobPollerConfig {
	private static final int DEFAULT_POLL_WAIT = 5000;// 默认等待时间

	@Setter
	private Integer waitTime = 0;// 等待时间（单位：秒）

	@Getter
	@Setter
	private boolean enabled = false;// 是否激活

	@Getter
	@Setter
	private JobQueueConfig jobQueueConfig;// 队列配置

	@Getter
	@Setter
	private JobInvokerConfig jobInvokerConfig;// 执行器配置

	public Integer getWaitTime() {
		return (waitTime > 0) ? waitTime : DEFAULT_POLL_WAIT;
	}
}
