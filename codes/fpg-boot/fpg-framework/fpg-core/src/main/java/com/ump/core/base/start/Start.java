/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.base.start;

import java.util.ArrayList;
import java.util.List;

import com.ump.commons.exception.StartupException;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-09-02 20:23:09
 *
 */
public class Start implements Runnable {
	private Classpath classpath = new Classpath(System.getProperty("java.class.path"));
	private ClassLoader classloader = null;
	private List<StartupLoader> loaders = null;
	private Config config = null;
	private String[] loaderArgs;

	public void init(String[] args, boolean fullInit) {

		String firstArgs = args.length > 0 ? args[0] : "";
		String cfgFile = Start.getConfigFileName(firstArgs);
		this.loaders = new ArrayList<>();
		this.config = new Config();
		config.readConfig(cfgFile);

		if (args.length > 1) {
			this.loaderArgs = new String[args.length - 1];
			System.arraycopy(args, 1, loaderArgs, 0, this.loaderArgs.length);
		}
		if (fullInit) {
			initClasspath();
			initStartLoaders();
		}
	}

	private void initClasspath() {
		System.setProperty("java.class.path",this.classpath.toString());
		this.classloader = this.classpath.getClassLoader();
		Thread.currentThread().setContextClassLoader(classloader);
	}

	public void init(String[] args) {
		this.init(args, true);
	}

	private void initStartLoaders() {
		for (String loaderClassName : config.loaders) {
			try {
				Class<?> loaderClass = classloader.loadClass(loaderClassName);
				StartupLoader loader = (StartupLoader) loaderClass.newInstance();
				loader.load(config, loaderArgs);
				loaders.add(loader);
			} catch (Exception e) {
				System.exit(99);
			}
		}

	}

	private void startStartLoaders() throws StartupException {
		for (StartupLoader loader : loaders) {
			loader.start();
		}
	}

	private static String getConfigFileName(String command) {
		if (null == command || command.trim().length() == 0) {
			command = "start";
		}
		if (command.startsWith("-")) {
			command = command.substring(1);
		}
		return "config/start/" + command + ".properties";
	}

	public static void main(String[] args) {
		//String firstArgs = args.length > 0 ? args[0] : "";
		Start start = new Start();
		start.init(args);
		start.start();
	}

	private void start() {
		try {
			startStartLoaders();
		} catch (StartupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
