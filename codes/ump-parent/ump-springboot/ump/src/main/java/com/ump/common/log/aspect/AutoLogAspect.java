/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.common.log.aspect;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.ump.common.log.LogCodeEnum;
import com.ump.common.log.LogEvent;
import com.ump.common.log.annotation.AutoLog;
import com.ump.commons.util.ObjectUtil;
import com.ump.entity.sys.LogEntity;

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

	@Autowired
	private ApplicationContext applicationContext;
	private static final ThreadLocal<Long> beginTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal beginTime");

	@PostConstruct
	public void init() {
		logger.info("Loading to monitor log AOP....");
	}

	@Pointcut("@annotation(com.ump.common.log.annotation.AutoLog)")
	public void logPointCut() {
	}

	@Before("logPointCut()")
	public void before(JoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		beginTimeThreadLocal.set(beginTime);
	}

	@AfterReturning(returning = "ret", pointcut = "logPointCut()")
	public void doAfterReturning(JoinPoint point, Object ret) throws NoSuchMethodException, SecurityException {
		// 处理完请求，返回内容
//		R r = Convert.convert(R.class, ret);
//		if (r.getCode() == 200) {
//			// 正常返回
//			sysLog.setType(1);
//		} else {
//			sysLog.setType(2);
//			sysLog.setExDetail(r.getMsg());
//		}
		// 发布事件
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// logName logType requestUrl requestType requestParam costTime
		Map<String, String[]> requestParams = request.getParameterMap();
		String requestType = request.getMethod();
		String requestUrl = request.getRequestURI();

		Object targetObj = point.getTarget();
		Signature sign = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) sign;
		String methodName = sign.getName();
		Class<?>[] parameterTypes = methodSignature.getParameterTypes();

		Method m = targetObj.getClass().getMethod(methodName, parameterTypes);
		AutoLog autoLog = m.getAnnotation(AutoLog.class);
		if (null == autoLog) {
			return;
		}
		LogCodeEnum logCode = autoLog.logCode();
		if (null == logCode) {
			return;
		}
		String remark = autoLog.remark();
		LogEntity log = new LogEntity();
		log.setLogId(UUID.randomUUID().toString().replace("-", ""));
		log.setLogName(logCode.getDescription());
		log.setIpAddress(getIpAddr(request));
		log.setLogType(logCode.getLogType().getCode());
		log.setLogMoudle(logCode.getCode());
		log.setRequestUrl(requestUrl);
		log.setRequestType(requestType);
		log.setRequestMethod(methodName);
		log.setRequestParams(ObjectUtil.mapToString(requestParams));
		// 执行时长(毫秒)
		long beginTime = beginTimeThreadLocal.get();
		long time = System.currentTimeMillis() - beginTime;
		log.setRemark(remark);
		log.setCostTime(time);
		logger.info("monitor log duration:{} ms", time);
		switch (logCode.getLogType()) {
		case BUSSLOG:
			break;
		default:
			break;
		}
		log.setResult(JSON.toJSONString(ret));
		applicationContext.publishEvent(new LogEvent(log));
	}

	@AfterThrowing(pointcut = "logPointCut()", throwing = "ex")
	public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("连接点方法为：" + methodName + ",参数为：" + args + ",异常为：" + ex);

		LogEntity log = new LogEntity();
		log.setLogId(UUID.randomUUID().toString().replace("-", ""));

		applicationContext.publishEvent(new LogEvent(log));
	}

//	private String getIpAddr(HttpServletRequest request) {
//		String ip = request.getHeader("x-forwarded-for");
//		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
//			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
//			if (ip.indexOf(",") != -1) {
//				ip = ip.split(",")[0];
//			}
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("HTTP_CLIENT_IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("X-Real-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//		}
//		return ip;
//	}

	/**
	 * 获取客户端IP地址
	 * 
	 * @param request 请求
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if ("127.0.0.1".equals(ip)) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		return ip;
	}
}
