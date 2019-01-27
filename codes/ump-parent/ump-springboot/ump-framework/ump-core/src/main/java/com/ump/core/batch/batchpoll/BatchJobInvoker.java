package com.ump.core.batch.batchpoll;

import java.util.Date;

import com.ump.core.batch.BatchJob;
import com.ump.core.batch.config.JobInvokerConfig;

public class BatchJobInvoker implements Runnable {
	private JobInvokerConfig jobInvokerConfig;
	protected BatchJobPoller jp;
	protected Thread thread;
	protected Date created;
	protected int jobRunTimes = 0;
	protected boolean run = false;

	public BatchJobInvoker(BatchJobPoller jp, JobInvokerConfig jobInvokerConfig) {
		this.jobInvokerConfig = jobInvokerConfig;
		this.jp = jp;
		this.created = new Date();
		this.run = true;
		this.jobRunTimes = 0;

	}

	protected BatchJobInvoker() {
	}

	@Override
	public void run() {
		BatchJob job = jp.next();
		if (null != job && job.isValid()) {
			job.execute();
		}

	}

}
