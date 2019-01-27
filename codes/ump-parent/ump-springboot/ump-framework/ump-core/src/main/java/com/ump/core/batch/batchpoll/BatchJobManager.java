package com.ump.core.batch.batchpoll;

import com.ump.commons.exception.BatchGenericException;
import com.ump.core.batch.config.JobPollerConfig;

public class BatchJobManager {
	private static BatchJobManager bjm;

	private BatchJobManager() {
	}

	public static BatchJobManager getInstance() {
		if (null == bjm) {
			throw new RuntimeException("");
		}
		return bjm;
	}

	public static void initInstance() throws BatchGenericException {
		bjm = new BatchJobManager();
		bjm.init();
	}

	private void init() throws BatchGenericException {
		// 1.
		// 2.
		BatchJobPoller taskJobPoller = new BatchTaskPoller(this, getPollerConfig(BatchJobType.TASK_JOB.name()),
				BatchJobType.TASK_JOB);

		BatchJobPoller stepJobPoller = new BatchTaskPoller(this, getPollerConfig(BatchJobType.STEP_JOB.name()),
				BatchJobType.STEP_JOB);

	}

	private JobPollerConfig getPollerConfig(String jobType) {
		// TODO Auto-generated method stub
		return null;
	}

}
