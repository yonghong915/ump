package com.ump.core.batch.batchpoll;

import com.ump.core.batch.BatchJob;
import com.ump.core.batch.batchstep.BatchStepScanner;

/**
 * 扫描器工厂
 * 
 * @author fangyh
 *
 */
public class BatchJobScannerFactory {

	/**
	 * 创建作业扫描器
	 * 
	 * @param jobType   作业类型
	 * @param jobPoller 作业调度器
	 * @return
	 */
	
	public static IScanner<BatchJob> createJobScanner(BatchJobType jobType, BatchJobPoller jobPoller) {
		IScanner<BatchJob> jobScanner = null;
		if (BatchJobType.TASK_JOB.equals(jobType)) {
			jobScanner = new BatchTaskScanner(jobPoller);
		} else if (BatchJobType.STEP_JOB.equals(jobType)) {
			jobScanner = new BatchStepScanner(jobPoller);
		} else {
		}
		return jobScanner;
	}
}
