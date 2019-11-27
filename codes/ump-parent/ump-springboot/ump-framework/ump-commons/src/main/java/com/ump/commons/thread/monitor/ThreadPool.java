package com.ump.commons.thread.monitor;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	private static ExecutorService service = Executors.newFixedThreadPool(5);
	private static final ThreadPoolExecutor executor = createThreadPoolExecutor();
//	private static MonitorQueue monitorQueue = MonitorQueue.getInstance();
	private static Queue<MonitorMessage> queue = null;

	private static final ThreadPool instance = new ThreadPool();

	private ThreadPool() {
		getQueueInstance();
	}

	public static ThreadPool getInstance() {
		return instance;
	}

	public Queue<MonitorMessage> getQueueInstance() {
		if (null == queue) {
			queue = new ConcurrentLinkedQueue<MonitorMessage>();
		}
		return queue;
	}

	private static ThreadPoolExecutor createThreadPoolExecutor() {
		return new ThreadPoolExecutor(5, 5, 6000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}

	public void queueNow(Producer producer) {
		// job.queue();
		try {
			executor.execute(producer);
		} catch (Exception e) {
		}
	}

//	public void putProducer(MonitorMessage msg) {
//		System.out.println("queue producer=" + queue.hashCode());
//		Producer producer = new Producer(queue, msg);
//		executeThread(producer);
//	}
//
//	public void putConsumer() {
//		System.out.println("queue Consumer=" + queue.hashCode());
//		Consumer consumer = new Consumer(queue);
//		executeThread(consumer);
//	}
//
//	public static void get() {
//		Consumer producer = new Consumer(monitorQueue);
//		executeThread(producer);
//	}

	public static void executeThread(Runnable task) {
		service.execute(task);
	}
}
