package com.ump.core.base.start;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.ump.commons.exception.ContainerException;
import com.ump.commons.exception.StartupException;
import com.ump.core.base.container.Container;

public class Start implements Runnable {
	private Config config = null;
	private final AtomicReference<ServerState> serverState = new AtomicReference<ServerState>(ServerState.STARTING);

	// Singleton, do not change
	private static final Start instance = new Start();

	private Start() {
	}

	public static void main(String[] args) {
		System.out.println("Start...");
		List<StartupCommand> ofbizCommands = null;
		try {
			ofbizCommands = StartupCommandUtil.parseOfbizCommands(args);
		} catch (StartupException e) {
			// incorrect arguments passed to the command line
			System.exit(1);
		}
		CommandType commandType = determineCommandType(ofbizCommands);
		if (!commandType.equals(CommandType.HELP)) {
			instance.config = StartupControlPanel.init(ofbizCommands);
		}

		switch (commandType) {
		case HELP:
			//StartupCommandUtil.printOfbizStartupHelp(System.out);
			break;
		case STATUS:
			//System.out.println("Current Status : " + AdminClient.requestStatus(instance.config));
			break;
		case SHUTDOWN:
			//System.out.println("Shutting down server : " + AdminClient.requestShutdown(instance.config));
			break;
		case START:
			try {
				StartupControlPanel.start(instance.config, instance.serverState, ofbizCommands);
			} catch (StartupException e) {
				//StartupControlPanel.fullyTerminateSystem(e);
			}
			break;
		}
	}

	/*
	 * Returns the <code>Start</code> instance.
	 */
	public static Start getInstance() {
		return instance;
	}

	/**
	 * Returns the server's main configuration.
	 */
	public Config getConfig() {
		return this.config;
	}

	/**
	 * Returns the server's current state.
	 */
	public ServerState getCurrentState() {
		return serverState.get();
	}

	@Override
	public void run() {
		Object obj = null;
		String className = "com.ump.core.base.container.BatchContainer";
		try {
			Class<?> clazz = Class.forName(className);
			obj = clazz.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (obj instanceof Container) {
			Container container = (Container) obj;
			try {
				container.init(null, null);
			} catch (ContainerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

//	private void initStartLoaders() {
//		// initialize the loaders
//		List<String> loaders = null;
//		loaders.forEach((String loaderClassName) -> {
//			Class<?> loaderClass = classloader.loadClass(loaderClassName);
//			StartupLoader loader = (StartupLoader) loaderClass.newInstance();
//			loader.load(config, args);
//		});
//
//	}

	private void startStartLoaders() {
		// start the loaders
	}

	/**
	 * This enum contains the possible OFBiz server states.
	 */
	public enum ServerState {
		STARTING, RUNNING, STOPPING;

		@Override
		public String toString() {
			return name().charAt(0) + name().substring(1).toLowerCase();
		}
	}

	private static CommandType determineCommandType(List<StartupCommand> ofbizCommands) {
		if (ofbizCommands.stream()
				.anyMatch(command -> command.getName().equals(StartupCommandUtil.StartupOption.HELP.getName()))) {
			return CommandType.HELP;
		} else if (ofbizCommands.stream()
				.anyMatch(command -> command.getName().equals(StartupCommandUtil.StartupOption.STATUS.getName()))) {
			return CommandType.STATUS;
		} else if (ofbizCommands.stream()
				.anyMatch(command -> command.getName().equals(StartupCommandUtil.StartupOption.SHUTDOWN.getName()))) {
			return CommandType.SHUTDOWN;
		} else {
			return CommandType.START;
		}
	}

	private enum CommandType {
		HELP, STATUS, SHUTDOWN, START
	}
}
