package com.ump.core.base.start;

import java.util.List;

import com.ump.commons.exception.StartupException;

/**
 * Interface for loading server startup classes
 * 
 * @author fangyh
 *
 */
public interface StartupLoader {
	/**
	 * Load a startup class
	 * 
	 * @param config
	 * @param args
	 * @throws StartupException
	 */
	public void load(Config config, List<StartupCommand> ofbizCommands) throws StartupException;

	/**
	 * Start a startup class
	 * 
	 * @return
	 * @throws StartupException
	 */
	public boolean start() throws StartupException;

	/**
	 * Stop a startup class
	 * 
	 * @throws StartupException
	 */
	public void unload() throws StartupException;
}
