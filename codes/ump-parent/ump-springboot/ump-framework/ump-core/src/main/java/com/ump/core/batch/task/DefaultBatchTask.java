package com.ump.core.batch.task;

import com.ump.core.batch.BatchAbstractJob;
import com.ump.core.batch.BatchTaskContext;
import com.ump.core.batch.batchpoll.BatchJobPoller;
import com.ump.core.batch.batchpoll.BatchJobType;

public class DefaultBatchTask extends BatchAbstractJob {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BatchJobPoller jp;
	private BatchTaskContext taskContext;

	public DefaultBatchTask(String jobId, String jobName, BatchJobPoller jp) {
		super(jobId, jobName, BatchJobType.TASK_JOB);
		this.jp = jp;
		this.taskContext = new BatchTaskContext();
		this.taskContext.setJobId(jobId);

		jobType = BatchJobType.TASK_JOB;

	}

	@Override
	public void execute() {
		long start = System.currentTimeMillis();
		initTask();
		runTask();

	}

	private void runTask() {
		// TODO Auto-generated method stub

	}

	private void initTask() {
		createTaskSteps();
		initTaskContext();

	}

	private void initTaskContext() {
		// TODO Auto-generated method stub

	}

	private void createTaskSteps() {
		// TODO Auto-generated method stub

	}

}
