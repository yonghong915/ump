package com.ump.thread;

import java.util.Random;
import java.util.concurrent.Callable;

public class SmsThread implements Callable<String> {

	@Override
	public String call() throws Exception {
		Integer res = new Random().nextInt(100);
		Thread.sleep(1000);
		String threadName = Thread.currentThread().getName();
		System.out.println("线程：" + threadName + ",正在执行第" + "个任务");
		System.out.println("任务执行:获取到结果 :" + res);
		return String.valueOf(res);
	}

}
