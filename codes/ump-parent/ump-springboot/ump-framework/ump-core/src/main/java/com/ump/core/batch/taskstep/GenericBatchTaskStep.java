package com.ump.core.batch.taskstep;

import java.util.Map;

import com.ump.commons.exception.BatchGenericException;
import com.ump.core.batch.BatchAbstractJob;
import com.ump.core.batch.BatchTaskContext;
import com.ump.core.batch.batchpoll.BatchJobPoller;

public abstract class GenericBatchTaskStep extends BatchAbstractJob {

	public GenericBatchTaskStep(String jobId, String jobName, BatchJobPoller jobPoller) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map<String, Object> inParam;
	BatchTaskContext taskContext;

	@Override
	public void execute() {
		boolean isSuccess = runBusiness();

	}

	protected abstract boolean runBusiness() throws BatchGenericException;
}
