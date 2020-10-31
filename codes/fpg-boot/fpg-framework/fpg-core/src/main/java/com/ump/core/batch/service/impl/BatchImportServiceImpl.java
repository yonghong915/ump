package com.ump.core.batch.service.impl;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ump.core.batch.imp.IHandler;


@Service("batchImportService")
public class BatchImportServiceImpl implements IHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	@Override
	public void execute(Map<String, Object> paramMap) {
		Runnable runnable = () -> {
			logger.info("paramMap={}", paramMap);
		};
		executorService.execute(runnable);
	}

}
