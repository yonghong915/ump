package com.ump.core.batch.batchpoll;

import java.util.List;

import com.ump.core.batch.BatchJob;

/**
 * 作业队列
 * 
 * @author fangyh
 *
 */
public interface IQueue {
	void queueNow(BatchJob job);

	void queueNow(List<BatchJob> jobs);

	BatchJob nextJob();

	int getSize();

	int getFreeSize();

	int getExtractCount();
}
