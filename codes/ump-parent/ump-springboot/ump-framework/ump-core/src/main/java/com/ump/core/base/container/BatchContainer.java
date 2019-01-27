package com.ump.core.base.container;

import com.ump.commons.exception.ContainerException;
import com.ump.core.batch.batchpoll.BatchJobManager;

public class BatchContainer implements Container {

	@Override
	public void init(String[] args, String configFile) throws ContainerException {
		BatchJobManager.initInstance();
	}

	@Override
	public boolean start() throws ContainerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stop() throws ContainerException {
		// TODO Auto-generated method stub

	}

}
