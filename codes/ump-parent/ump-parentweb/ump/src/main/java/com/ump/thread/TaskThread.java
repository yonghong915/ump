package com.ump.thread;

import static com.google.common.base.Preconditions.checkNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskThread implements Runnable {
    private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void run() {
		String str = "aaa";
		checkNotNull(str);
		String threadName = Thread.currentThread().getName();
		logger.info("线程：{},正在执行第个任务",threadName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			logger.error("err={}",e);
		}
	}

}
