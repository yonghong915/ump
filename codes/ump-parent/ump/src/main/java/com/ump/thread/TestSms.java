package com.ump.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class TestSms {

	public static void main(String[] args) {
		ExecutorService executorService = ThreadInstance.getInstance().getExecutorService();

		FutureTask<String> futureTask = new FutureTask<String>(new SmsThread());
		executorService.submit(futureTask);

		// if (futureTask.isDone()) {
		// try {
		// System.out.println(futureTask.get(1000,TimeUnit.MILLISECONDS));
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (ExecutionException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (TimeoutException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
	}

}
