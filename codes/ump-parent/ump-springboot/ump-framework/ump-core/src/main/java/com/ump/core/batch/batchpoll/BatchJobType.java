package com.ump.core.batch.batchpoll;

/**
    * 作业类型枚举
 * 
 * @author fangyh
 *
 */
public enum BatchJobType {
	TASK_JOB(1), // 任务类型作业
	STEP_JOB(2), // 步骤类型作业
	DATA_JOB(3);// 数据类型作业
	public final int value;

	private BatchJobType(int value) {
		this.value = value;
	}
}
