package com.ump.core.base.start;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import com.ump.commons.exception.StartupException;
import com.ump.core.base.start.Start.ServerState;

final class StartupControlPanel {
	static Config init(List<StartupCommand> ofbizCommands) {
		Config config = null;
		try {
			loadGlobalOfbizSystemProperties("ofbiz.system.props");
			config = new Config(ofbizCommands);
		} catch (StartupException e) {
			fullyTerminateSystem(e);
		}
		return config;
	}

	static void fullyTerminateSystem(StartupException e) {
		e.printStackTrace();
		System.exit(1);
	}

	static void start(Config config, AtomicReference<ServerState> serverState, List<StartupCommand> ofbizCommands)
			throws StartupException {

		List<StartupLoader> loaders = new ArrayList<StartupLoader>();
		//Thread adminServer = createAdminServer(config, serverState, loaders);
		Classpath classPath = createClassPath(config);
		NativeLibClassLoader classLoader = createAndSetContextClassLoader(config, classPath);

		createLogDirectoryIfMissing(config);
		createRuntimeShutdownHook(config, loaders, serverState);
		loadStartupLoaders(config, loaders, ofbizCommands, serverState, classLoader);
		//executeShutdownAfterLoadIfConfigured(config, loaders, serverState, adminServer);
	}

	private static void loadGlobalOfbizSystemProperties(String globalOfbizPropertiesFileName) throws StartupException {
		String systemProperties = System.getProperty(globalOfbizPropertiesFileName);
		if (systemProperties != null) {
			FileInputStream stream = null;
			try {
				stream = new FileInputStream(systemProperties);
				System.getProperties().load(stream);
				stream.close();
			} catch (IOException e) {
				throw new StartupException("Couldn't load global system props", e);
			}
		}
	}

	private static Classpath createClassPath(Config config) throws StartupException {
		Classpath classPath = new Classpath();
		try {
			classPath.addComponent(config.ofbizHome);
			String ofbizHomeTmp = config.ofbizHome;
			if (!ofbizHomeTmp.isEmpty() && !ofbizHomeTmp.endsWith("/")) {
				ofbizHomeTmp = ofbizHomeTmp.concat("/");
			}
			if (config.classpathAddComponent != null) {
				String[] components = config.classpathAddComponent.split(",");
				for (String component : components) {
					classPath.addComponent(ofbizHomeTmp.concat(component.trim()));
				}
			}
		} catch (IOException e) {
			throw new StartupException("Cannot create classpath", e);
		}
		return classPath;
	}

	private static NativeLibClassLoader createAndSetContextClassLoader(Config config, Classpath classPath)
			throws StartupException {
		ClassLoader parent = Thread.currentThread().getContextClassLoader();
		NativeLibClassLoader classloader = null;
		try {
			classloader = new NativeLibClassLoader(classPath.getUrls(), parent);
			classloader.addNativeClassPath(System.getProperty("java.library.path"));
			for (File folder : classPath.getNativeFolders()) {
				classloader.addNativeClassPath(folder);
			}
		} catch (IOException e) {
			throw new StartupException("Couldn't create NativeLibClassLoader", e);
		}
		Thread.currentThread().setContextClassLoader(classloader);
		return classloader;
	}

	private static void createLogDirectoryIfMissing(Config config) {
		File logDir = new File(config.logDir);
		if (!logDir.exists()) {
			if (logDir.mkdir()) {
				System.out.println("Created OFBiz log dir [" + logDir.getAbsolutePath() + "]");
			}
		}
	}

	private static void createRuntimeShutdownHook(Config config, List<StartupLoader> loaders,
			AtomicReference<ServerState> serverState) {

		if (config.useShutdownHook) {
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					//shutdownServer(loaders, serverState, this);
				}
			});
		} else {
			System.out.println("Shutdown hook disabled");
		}
	}

	private static void loadStartupLoaders(Config config, List<StartupLoader> loaders,
			List<StartupCommand> ofbizCommands, AtomicReference<ServerState> serverState,
			NativeLibClassLoader classloader) throws StartupException {

		synchronized (loaders) {
			for (Map<String, String> loaderMap : config.loaders) {
				if (serverState.get() == ServerState.STOPPING) {
					return;
				}
				try {
					String loaderClassName = loaderMap.get("class");
					Class<?> loaderClass = classloader.loadClass(loaderClassName);
					StartupLoader loader = (StartupLoader) loaderClass.newInstance();
					loaders.add(loader); // add before loading, so unload can occur if error during loading
					loader.load(config, ofbizCommands);
				} catch (ReflectiveOperationException e) {
					throw new StartupException(e.getMessage(), e);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String path : classloader.getNativeLibPaths()) {
			if (sb.length() > 0) {
				sb.append(File.pathSeparator);
			}
			sb.append(path);
		}
		System.setProperty("java.library.path", sb.toString());
		serverState.compareAndSet(ServerState.STARTING, ServerState.RUNNING);
	}

//	private static void executeShutdownAfterLoadIfConfigured(Config config, List<StartupLoader> loaders,
//			AtomicReference<ServerState> serverState, Thread adminServer) {
//
//		if (config.shutdownAfterLoad) {
//			stop(loaders, serverState, adminServer);
//		}
//	}
}
