package com.ump.commons.batch.imp;

/**
 * 
 * @author fangyh
 *
 */
public abstract class AbstractBaseBatch implements IHandler {

	/**
	 * 
	 */
	public void execute() {
		done();
	}

	/**
	 * 
	 */
	protected abstract void beforeDone();

	/**
	 * 
	 */
	protected abstract void done();

	/**
	 * 
	 */
	protected abstract void afterDone();

}
