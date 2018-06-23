package com.ump.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadPool {
	public void testThreadPool() {
		int a = Runtime.getRuntime().availableProcessors();
		System.out.println(a);
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 1; i < 10; i++) {
			fixedThreadPool.execute(new TaskThread());
		}
	}

	public void testFuture() throws InterruptedException, ExecutionException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		System.out.println("====提交异步任务");
		// Future<HashMap<String, String>> future = threadPool.submit(new
		// Callable<HashMap<String, String>>() {
		//
		// @Override
		// public HashMap<String, String> call() throws Exception {
		//
		// System.out.println("异步任务开始执行....");
		// Thread.sleep(2000);
		// System.out.println("异步任务执行完毕，返回执行结果!!!!");
		//
		// return new HashMap<String, String>() {
		// {
		// this.put("futureKey", "成功获取future异步任务结果");
		// }
		// };
		// }
		//
		// });

		FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
			public String call() {
				return "成功获取future异步任务结果";
			}
		});

		threadPool.execute(future);

		System.out.println("====提交异步任务之后，立马返回到主线程继续往下执行");
		Thread.sleep(1000);

		System.out.println("====此时需要获取上面异步任务的执行结果");

		boolean flag = true;
		while (flag) {
			// 异步任务完成并且未被取消，则获取返回的结果
			if (future.isDone() && !future.isCancelled()) {
				//HashMap<String, String> futureResult = future.get();
				String ret = future.get();
				System.out.println("====异步任务返回的结果是：" + ret);
				flag = false;
			}
		}
	}

	public static void main(String[] args) {
		ThreadPool t = new ThreadPool();
		try {
			t.testFuture();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}