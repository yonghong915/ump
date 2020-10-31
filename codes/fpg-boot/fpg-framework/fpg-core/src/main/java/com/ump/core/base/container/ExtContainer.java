/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.base.container;

import com.ump.commons.exception.ContainerException;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-09-04 20:47:41
 *
 */
public class ExtContainer implements Container {

	@Override
	public void init(String[] args, String configFile) throws ContainerException {
		System.out.println("ExtContainer init...");

	}

	@Override
	public boolean start() throws ContainerException {
		System.out.println("ExtContainer start...");
		return false;
	}

	@Override
	public void stop() throws ContainerException {
		// TODO Auto-generated method stub

	}

}
