package com.ump.core.batch.batchstep;

import java.util.List;
import java.util.Map;

import com.ump.commons.exception.BatchGenericException;
import com.ump.core.batch.BatchJob;
import com.ump.core.batch.batchpoll.BatchJobPoller;
import com.ump.core.batch.batchpoll.IScanner;

/**
 * 批量任务扫描器
 * 
 * @author fangyh
 *
 */
public class BatchStepScanner implements IScanner<BatchJob> {
	/** 作业调度器 */
	private BatchJobPoller jp = null;

	public BatchStepScanner(BatchJobPoller jp) {
		this.jp = jp;
	}

	@Override
	public List<BatchJob> scan() throws BatchGenericException {
		List<BatchJob> returnStepList = null;
		Map<String, Object> returnLoadMap = null;
		//扫描待处理任务
		
		return null;
	}
}
