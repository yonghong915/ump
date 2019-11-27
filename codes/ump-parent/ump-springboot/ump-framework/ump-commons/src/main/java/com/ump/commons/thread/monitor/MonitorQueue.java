package com.ump.commons.thread.monitor;

import java.io.ObjectStreamException;
import java.util.Queue;

public class MonitorQueue {
	private Queue<Object> queue = QueueInstance.getInstance();

	private MonitorQueue() {
	}

	private static class SingletonHolder {
		public final static MonitorQueue instance = new MonitorQueue();
	}

	public static MonitorQueue getInstance() {
		return SingletonHolder.instance;
	}

	protected Object readResolve() throws ObjectStreamException {
		return SingletonHolder.instance;
	}

	public void produce(Object obj) {
		System.out.println("queue="+queue.hashCode()+" "+queue.size());
		queue.add(obj);
	}

	// 消费苹果，从篮子中取走
	public Object consume() {
		if (!queue.isEmpty()) {
			return queue.poll();
		}
		return null;
	}
}
