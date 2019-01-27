package com.ump.core.batch;

import java.io.Serializable;

import com.ump.commons.exception.BatchGenericException;
import com.ump.core.batch.batchpoll.BatchJobType;

/**
 * 
 * @author fangyh
 * @since 2019-01-10
 * @version 1.0
 */
public interface BatchJob extends Serializable {
	void execute() throws BatchGenericException;

	String getJobId();

	String getJobName();

	long getRuntime();

	boolean isValid();

	BatchJobType getJobType();
}
