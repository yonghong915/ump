package com.ump.cfn.batch;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.ump.core.batch.imp.BatchExecutor;
import com.ump.core.batch.imp.IUpload;

public class FileTrace implements IUpload {
	private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

	public FileTrace(Map<String, Object> paramMap) {
	}

	@Override
	public void afterUpload() {
		// 文件上传后插入表

		executeBatchImport();

	}

	private void executeBatchImport() {
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				new Thread(new BatchExecutor("com.ump.cfn.batch.FileBatchImport", null)).start();
				return 1;
			}
		};

		// for (int i = 0; i < 10; i++) {
		// 创建一个异步任务
		FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
		// 提交异步任务到线程池，让线程池管理任务 特爽把。
		// 由于是异步并行任务，所以这里并不会阻塞
		executorService.submit(futureTask);
		// }
	}

}
