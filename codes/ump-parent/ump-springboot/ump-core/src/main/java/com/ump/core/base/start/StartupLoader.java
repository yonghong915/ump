package com.ump.core.base.start;

import com.ump.commons.exception.StartupException;

public interface StartupLoader {
	public void init(Start.Config config, String[] args) throws StartupException;

	public boolean start() throws StartupException;

	public void unload() throws StartupException;
}
