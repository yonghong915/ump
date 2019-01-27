package com.ump.core.batch.service.impl;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ump.commons.batch.imp.IHandler;
import com.ump.core.util.EsbUtil;

@Service("esbService")
public class EsbServiceImpl implements IHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	@Override
	public void execute(Map<String, Object> paramMap) {
		Callable<String> runnable = () -> {
			logger.info("paramMap={}", paramMap);
			String reqXml = (String) paramMap.get("esbReq");
			String esbResp = EsbUtil.callEsbService(reqXml);
			return esbResp;
		};
		Future<String> future = executorService.submit(runnable);
		try {
			String obj = future.get(3000, TimeUnit.MILLISECONDS);
			logger.info("esbResp={}", obj);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {

		}
	}

}
