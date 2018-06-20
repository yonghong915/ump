package com.ump.thread;

import static com.google.common.base.Preconditions.checkNotNull;

public class TaskThread implements Runnable {

	@Override
	public void run() {
		// Preconditions.checkArgument(expression);
		String str = "aaa";
		checkNotNull(str);
		String threadName = Thread.currentThread().getName();
		System.out.println("线程：" + threadName + ",正在执行第" + "个任务");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
