package com.ump.core.batch.taskstep;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.ump.commons.exception.BatchGenericException;
import com.ump.commons.exception.GenericServiceException;
import com.ump.core.batch.batchpoll.BatchJobPoller;

public class UserDefinedTaskStep extends GenericBatchTaskStep {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDefinedTaskStep(String jobId, String jobName, BatchJobPoller jobPoller) {
		super(jobId, jobName, jobPoller);
	}

	@Override
	protected boolean runBusiness() throws BatchGenericException {
		boolean isSuccess = false;
		String serviceName = null;
		Map<String, Object> filterStepParamMap;
		Map<String, Object> returnServiceMap;
		long start = System.currentTimeMillis();
		Map<String, Object> baseMap = taskContext.getBaseMap();
		Map<String, Object> extendMap = taskContext.getUserMap();
		Map<String, Object> inputMap = taskContext.getInputMap();
		Map<String, Object> newBaseMap = null;
		newBaseMap.putAll(baseMap);
		Map<String, Object> newInputMap;
		if (Objects.nonNull(inputMap)) {
			newInputMap = new HashMap<>();
			newInputMap.putAll(inputMap);
		}
		serviceName = (String) inParam.get("serviceName");
		String serviceParam = (String) inParam.get("serviceParam");
		try {
			isSuccess = true;
		} catch (GenericServiceException e) {
			throw new BatchGenericException();
		} finally {

		}
		return isSuccess;
	}

}
