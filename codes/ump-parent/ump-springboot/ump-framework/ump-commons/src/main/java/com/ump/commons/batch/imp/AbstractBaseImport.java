package com.ump.commons.batch.imp;

public abstract class AbstractBaseImport extends AbstractBaseBatch {

	protected abstract void validateParams();

	protected abstract void validateBizs();

	protected abstract void handleLine();

	protected abstract void afterHandleLine();

	public void done() {
		validateParams();// 判断文件

		beforeDone();// 初始化准备

		executeImport();

		afterDone();
	}

	private void executeImport() {
		int i = 0;
		do {
			validateBizs();
			handleLine();
			i++;
			afterHandleLine();
		} while (i < 5);
	}
}
