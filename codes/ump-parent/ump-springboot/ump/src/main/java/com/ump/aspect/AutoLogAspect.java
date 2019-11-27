/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.aspect;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-27 20:36:35
 *
 */
@Component
@Aspect
public class AutoLogAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		logger.info("Loading to monitor log AOP....");
	}

	@Pointcut("@annotation(com.ump.aspect.annotation.AutoLog)")
	public void logPointCut() {
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		logger.info("monitor log duration:{} ms", time);
		return result;
	}

	@AfterThrowing(pointcut = "logPointCut()", throwing = "ex")
	public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("连接点方法为：" + methodName + ",参数为：" + args + ",异常为：" + ex);

	}
}
