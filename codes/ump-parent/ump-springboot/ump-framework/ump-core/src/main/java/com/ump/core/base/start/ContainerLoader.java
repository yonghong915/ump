/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.base.start;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.ump.commons.exception.ContainerException;
import com.ump.commons.exception.StartupException;
import com.ump.core.base.container.Container;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-09-02 21:50:17
 *
 */
public class ContainerLoader implements StartupLoader {
	protected static boolean loaded = Boolean.FALSE.booleanValue();
	protected List<Container> loadedContainers = new LinkedList<>();
	protected String configFile = null;

	@Override
	public void load(Config config, String[] args) throws StartupException {
		System.out.println("ContainerLoader load...");
		loaded = Boolean.TRUE.booleanValue();
		this.configFile = config.containerConfig;
		Collection<ContainerConfig.Container> containers = null;
		try {
			containers = ContainerConfig.getContainer(configFile);
		} catch (ContainerException e) {
			throw new StartupException(e);
		}
		if (null != containers) {
			for (ContainerConfig.Container containerCfg : containers) {
				Container tmpContainer = loadContainer(containerCfg, args);
				loadedContainers.add(tmpContainer);
			}
		}
	}

	private Container loadContainer(ContainerConfig.Container containerCfg, String[] args) throws StartupException {
		ClassLoader loader = Classpath.getClassLoader();
		Class<?> containerClass = null;
		try {
			containerClass = loader.loadClass(containerCfg.className);
		} catch (ClassNotFoundException e) {
			throw new StartupException("Cannot locate container class", e);
		}
		if (null == containerClass) {
			throw new StartupException("Component container class not loaded");
		}
		Container containerObj = null;
		try {
			Object obj = containerClass.newInstance();
			if (obj instanceof Container) {
				containerObj = (Container) obj;
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new StartupException("cannot create " + containerCfg.name, e);
		}
		if (null == containerObj) {
			throw new StartupException("Unable to create instance of component container");
		}
		try {
			containerObj.init(args, configFile);
		} catch (ContainerException e) {
			throw new StartupException("Unable to init " + containerCfg.name, e);
		}
		return containerObj;
	}

	@Override
	public void start() throws StartupException {
		System.out.println("ContainerLoader start...");
		for (Container container : loadedContainers) {
			try {
				container.start();
			} catch (ContainerException e) {
				throw new StartupException("Cannot start()" + container.getClass().getName(), e);
			}
		}
	}

	@Override
	public void unload() throws StartupException {
		for (int i = loadedContainers.size(); i > 0; i--) {
			Container container = loadedContainers.get(i - 1);
			try {
				container.stop();
			} catch (ContainerException e) {
			}
		}

	}

	public static synchronized Container loadContainer(String config, String[] args) throws StartupException {
		if (!loaded) {
			ContainerLoader loader = new ContainerLoader();
			Config cfg = new Config();
			cfg.containerConfig = (config == null) ? "limited-containers.xml" : config;
			loader.load(cfg, args);
		}
		return null;
	}
}
