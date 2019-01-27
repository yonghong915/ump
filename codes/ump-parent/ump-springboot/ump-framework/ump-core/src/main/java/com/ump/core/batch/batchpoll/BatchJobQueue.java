package com.ump.core.batch.batchpoll;

import java.util.LinkedList;
import java.util.List;

import com.ump.core.batch.BatchJob;
import com.ump.core.batch.config.JobQueueConfig;

public class BatchJobQueue implements IQueue {
	private JobQueueConfig jobQueueConfig;
	private BatchJobPoller jp;
	private List<BatchJob> jobsQueue = new LinkedList<>();

	public BatchJobQueue(BatchJobPoller jp, JobQueueConfig jobQueueConfig) {
		this.jp = jp;
		this.jobQueueConfig = jobQueueConfig;
	}

	@Override
	public void queueNow(BatchJob job) {
		jobsQueue.add(job);
	}

	@Override
	public void queueNow(List<BatchJob> jobs) {
		jobsQueue.addAll(jobs);
	}

	@Override
	public BatchJob nextJob() {
		BatchJob job = null;
		if (jobsQueue.size() > 0) {
			job = jobsQueue.remove(0);
		}
		return job;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFreeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getExtractCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
