package com.ump.core.batch.config;

import lombok.Setter;

public class JobInvokerConfig {
	private static final long DEFAULT_THREAD_TTL = 18000000;// 默认生成时间
	private static final int DEFAULT_WAIT_TIME = 2000;// 默认等待时间
	private static final int DEFAULT_THREADS_COUNT = 15;// 默认线程数

	@Setter
	private long threadTll = 0;

	@Setter
	private long waitTime = 0;

	@Setter
	private long threadsCount = 0;

	public long getThreadTll() {
		return (threadTll > 0) ? threadTll : DEFAULT_THREAD_TTL;
	}

	public long getWaitTime() {
		return (waitTime > 0) ? waitTime : DEFAULT_WAIT_TIME;
	}

	public long getThreadsCount() {
		return (threadsCount > 0) ? threadsCount : DEFAULT_THREADS_COUNT;
	}
}
