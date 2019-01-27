package com.ump.core.batch.batchpoll;

import java.util.List;

import com.ump.commons.exception.BatchGenericException;
import com.ump.core.batch.BatchJob;

public interface IScanner<T> {
	List<BatchJob> scan() throws BatchGenericException;
}
