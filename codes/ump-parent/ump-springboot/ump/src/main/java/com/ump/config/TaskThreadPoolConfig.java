/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-01 09:54:31
 *
 */
@Configuration
@EnableAsync
public class TaskThreadPoolConfig {
	private static final Logger log = LoggerFactory.getLogger(TaskThreadPoolConfig.class);

	@Value("${thread.corePoolSize:10}")
	private int corePoolSize;

	@Value("${thread.corePoolSize:20}")
	private int maxPoolSize;

	@Value("${thread.queueCapacity:1000}")
	private int queueCapacity;

	@Value("${thread.keepAlive:60}")
	private int keepAlive;

	@Bean("asyncServiceExecutor")
	public Executor myTaskAsyncPool() {
		log.info("start TaskExecutePool myTaskAsyncPool");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		// 配置核心线程数
		executor.setCorePoolSize(corePoolSize);
		// 配置核心线程数
		executor.setMaxPoolSize(maxPoolSize);
		// 配置队列容量
		executor.setQueueCapacity(queueCapacity);
		// 设置线程活跃时间
		executor.setKeepAliveSeconds(keepAlive);
		// 设置线程名
		executor.setThreadNamePrefix("myTaskAsyn-");
		// 设置拒绝策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}
}
