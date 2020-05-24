/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.base.container;

import com.ump.commons.exception.ContainerException;
import com.ump.core.ws.config.WsdlConfigManager;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-08-11 18:37:26
 *
 */
public class WebserviceContainer implements Container {

	@Override
	public void init(String[] args, String configFile) throws ContainerException {
		WsdlConfigManager.init();
	}

	@Override
	public boolean start() throws ContainerException {
		return true;
	}

	@Override
	public void stop() throws ContainerException {

	}

}
