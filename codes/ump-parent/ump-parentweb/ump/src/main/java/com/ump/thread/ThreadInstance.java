package com.ump.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadInstance {

	private static volatile ThreadInstance instance = null;

	private ExecutorService executorService;

	private ThreadInstance() {
		if (null == executorService) {
			executorService = Executors.newFixedThreadPool(3);
		}
	}

	public static ThreadInstance getInstance() {
		if (instance == null) {
			synchronized (ThreadInstance.class) {
				if (instance == null) {// 2
					instance = new ThreadInstance();
				}
			}
		}
		return instance;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

}
