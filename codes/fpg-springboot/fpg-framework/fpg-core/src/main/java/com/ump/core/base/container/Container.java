package com.ump.core.base.container;

import com.ump.commons.exception.ContainerException;

/**
 * Interface for Container
 * 
 * @author fangyh
 *
 */
public interface Container {
	/**
	 * Initialize the container
	 * 
	 * @param args
	 * @param configFile
	 * @throws ContainerException
	 */
	public void init(String[] args, String configFile) throws ContainerException;

	/**
	 * Start the Container
	 * 
	 * @return
	 * @throws ContainerException
	 */
	public boolean start() throws ContainerException;

	/**
	 * Stop the Container
	 * 
	 * @throws ContainerException
	 */
	public void stop() throws ContainerException;
}
