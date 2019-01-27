package com.ump.core.batch;

import com.ump.commons.exception.BatchGenericException;
import com.ump.core.batch.batchpoll.BatchJobType;

import lombok.Getter;

public abstract class BatchAbstractJob implements BatchJob {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	protected long runtime = -1;

	@Getter
	protected String jobId;

	@Getter
	protected String jobName;

	@Getter
	protected BatchJobType jobType = null;

	protected BatchAbstractJob() {
	}

	public BatchAbstractJob(String jobId, String jobName, BatchJobType jobType) {
		this.jobId = jobId;
		this.jobName = jobName;
		this.jobType = jobType;
		this.runtime = System.currentTimeMillis();
	}

	public boolean isValid() {
		return runtime > 0;
	}

	public abstract void execute();
}
