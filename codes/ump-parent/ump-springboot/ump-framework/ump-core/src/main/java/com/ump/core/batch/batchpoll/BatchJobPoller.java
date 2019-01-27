package com.ump.core.batch.batchpoll;

import java.util.List;
import java.util.Objects;

import com.ump.core.batch.BatchJob;
import com.ump.core.batch.config.JobPollerConfig;

import lombok.Getter;

public class BatchJobPoller implements Runnable {
	protected Thread pollerThread;
	protected IScanner<BatchJob> jobScanner;

	@Getter
	protected BatchJobManager bjm;
	protected IQueue jobQueue;
	protected BatchJobType jobType;
	protected JobPollerConfig jobPollerConfig;
	protected List<BatchJobInvoker> jobInvokerPoll;
	protected volatile boolean isRunning = false;
	protected boolean isDelayFinished = false;

	protected BatchJobPoller() {
	}

	public BatchJobPoller(BatchJobManager bjm, JobPollerConfig jobPollerConfig, BatchJobType jobType) {
		this.jobPollerConfig = jobPollerConfig;
		this.bjm = bjm;
		this.jobType = jobType;
		this.jobScanner = BatchJobScannerFactory.createJobScanner(jobType, this);
		this.jobQueue = new BatchJobQueue(this, jobPollerConfig.getJobQueueConfig());
		this.jobInvokerPoll = createThreadPool();
		if (null != jobPollerConfig && jobPollerConfig.isEnabled()) {
			startPoll();
		}
	}

	private void startPoll() {
		if (null != jobPollerConfig && jobPollerConfig.isEnabled()) {
			pollerThread = new Thread(this, this.toString());
			pollerThread.setDaemon(false);
			this.isRunning = true;
			pollerThread.start();
		}
	}

	private List<BatchJobInvoker> createThreadPool() {
		// TODO Auto-generated method stub
		return null;
	}

	public BatchJob next() {
		BatchJob job = null;
		if (isRunning) {
			synchronized (jobQueue) {
				if (jobQueue.getSize() > 0) {
					job = jobQueue.nextJob();
				}
			}
		}
		return job;
	}

	@Override
	public void run() {
		delayRun();
		while (isRunning) {
			List<BatchJob> jobs = jobScanner.scan();
			if (Objects.nonNull(jobs)) {
				handleJobs(jobs);
			} else {
				try {
					this.wait(jobPollerConfig.getWaitTime());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private void handleJobs(List<BatchJob> jobs) {
		queueNow(jobs);
	}

	private void queueNow(List<BatchJob> jobs) {
		if (!isRunning) {
			return;
		}
		synchronized (jobQueue) {
			jobQueue.queueNow(jobs);
		}

		adjustJobInvoker();
	}

	private void adjustJobInvoker() {
		// TODO Auto-generated method stub

	}

	private void delayRun() {
		// TODO Auto-generated method stub

	}

}
