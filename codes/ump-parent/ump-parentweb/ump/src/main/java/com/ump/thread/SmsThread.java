package com.ump.thread;

import java.util.Random;
import java.util.concurrent.Callable;

public class SmsThread implements Callable<String> {

	@Override
	public String call() throws Exception {
		Integer res = new Random().nextInt(100);
		Thread.sleep(1000);
		return String.valueOf(res);
	}

}
