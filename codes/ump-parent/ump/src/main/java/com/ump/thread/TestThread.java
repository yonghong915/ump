package com.ump.thread;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestThread {
	public static void main(String[] args) {
		// ExecutorService priorityThreadPool = new ThreadPoolExecutor(3, 3, 0L,
		// TimeUnit.SECONDS,
		// new PriorityBlockingQueue<Runnable>());
		PausableThreadPoolExecutor pausableThreadPoolExecutor = new PausableThreadPoolExecutor(1, 1, 0L,
				TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());

		for (int i = 1; i <= 10; i++) {
			final int priority = i;
			pausableThreadPoolExecutor.execute(new PriorityRunnable(priority) {
				@Override
				public void doSth() {
					// String threadName = Thread.currentThread().getName();
					// System.out.println("线程：" + threadName + ",正在执行优先级为：" + priority + "的任务");
					// try {
					// Thread.sleep(2000);
					// } catch (InterruptedException e) {
					// e.printStackTrace();
					// }
//					runOnUiThread(new Runnable() {
//						@Override
//						public void run() {
//							textView.setText(priority + "");
//						}
//					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
