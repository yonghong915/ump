package com.ump.commons.thread.monitor;

import java.io.ObjectStreamException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueInstance {
	private QueueInstance() {
	}

	private static class SingletonHolder {
		public final static Queue<Object> instance = new ConcurrentLinkedQueue<Object>();
	}

	public static Queue<Object> getInstance() {
		return SingletonHolder.instance;
	}

	protected Object readResolve() throws ObjectStreamException {
		return SingletonHolder.instance;
	}
}
