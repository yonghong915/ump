/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.base.start;

import com.ump.commons.exception.StartupException;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-09-02 20:25:35
 *
 */
public interface StartupLoader {

	void start() throws StartupException;

	void load(Config config, String[] loaderArgs) throws StartupException;

	void unload() throws StartupException;

}
