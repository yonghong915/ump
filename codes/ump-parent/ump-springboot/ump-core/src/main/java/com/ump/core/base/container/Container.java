package com.ump.core.base.container;

import com.ump.commons.exception.ContainerException;

public interface Container {
	public void init(String[] args, String configFile) throws ContainerException;

	public boolean start() throws ContainerException;

	public void stop() throws ContainerException;
}
