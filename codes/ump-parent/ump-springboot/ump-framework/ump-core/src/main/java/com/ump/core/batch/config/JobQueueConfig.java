package com.ump.core.batch.config;

import lombok.Setter;

/**
 * 队列配置
 * 
 * @author fangyh
 *
 */
public class JobQueueConfig {
	private static final int DEFAULT_TOTAL_SIZE = 100;// 默认总大小
	private static final int DEFAULT_EXTRACT_LIMIT = 1000;// 默认提取数据

	@Setter
	private int totalSize = 0;

	@Setter
	private int extractLimit = 0;

	public long getTotalSize() {
		return (totalSize >= 0) ? totalSize : DEFAULT_TOTAL_SIZE;
	}

	public long getExtractLimit() {
		return (extractLimit >= 0) ? extractLimit : DEFAULT_EXTRACT_LIMIT;
	}
}
