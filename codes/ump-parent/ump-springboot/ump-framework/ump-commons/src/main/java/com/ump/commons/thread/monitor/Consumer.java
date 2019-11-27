package com.ump.commons.thread.monitor;

import java.io.BufferedWriter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Consumer implements Runnable {
	private Queue<MonitorMessage> queue;

	public Consumer(Queue<MonitorMessage> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			if (!queue.isEmpty()) {
				System.out.println(Thread.currentThread().getName() + " 获取数据：" + queue.poll().getMsg());
			}
		}

	}
}