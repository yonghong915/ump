package com.ump.cfn.batch;

import com.ump.core.batch.imp.AbstractBaseImport;

public class FileBatchImport extends AbstractBaseImport {

	@Override
	public void beforeDone() {
		System.out.println("FileBatch===beforeDone");
	}

	@Override
	public void afterDone() {
		System.out.println("FileBatch===afterDone");
	}

	@Override
	public void validateParams() {
		System.out.println("FileBatch===validParams");
	}

	@Override
	public void validateBizs() {
		System.out.println("FileBatch===validBizs");
	}

	@Override
	public void handleLine() {
		System.out.println("FileBatch===handleLine");
	}

	@Override
	protected void afterHandleLine() {
		System.out.println("FileBatch===afterHandleLine");

	}

}
