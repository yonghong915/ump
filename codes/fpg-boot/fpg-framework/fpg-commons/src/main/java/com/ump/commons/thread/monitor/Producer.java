package com.ump.commons.thread.monitor;

import java.util.Queue;

public class Producer implements Runnable {
	private Queue<MonitorMessage> queue;
	private MonitorMessage data;

	public Producer(Queue<MonitorMessage> queue, MonitorMessage data) {
		this.queue = queue;
		this.data = data;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " " + data.getMsg());
		queue.add(data);
	}
}
