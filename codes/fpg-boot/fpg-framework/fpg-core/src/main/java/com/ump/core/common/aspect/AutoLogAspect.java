package com.ump.core.common.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import com.ump.core.common.aspect.annotation.AutoLog;
import com.ump.core.modules.system.entity.SysLog;
import com.ump.core.modules.system.service.ISysLogService;

/**
 * 系统日志，切面处理类
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
@Aspect
//@Component
public class AutoLogAspect {
	@Autowired
	private ISysLogService sysLogService;

	@Pointcut("@annotation(com.ump.core.common.aspect.annotation.AutoLog)")
	public void logPointCut() {

	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		// 执行方法
		Object result = point.proceed();
		// 执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		// 保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLog sysLog = new SysLog();
		AutoLog syslog = method.getAnnotation(AutoLog.class);
		if (syslog != null) {
			// 注解上的描述,操作日志内容
			sysLog.setLogContent(syslog.value());
			sysLog.setLogType(syslog.logType());
		}

		// 请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		// 请求的参数
		Object[] args = joinPoint.getArgs();
		try {
			//String params = JSONObject.toJSONString(args);
			//sysLog.setRequestParam(params);
		} catch (Exception e) {

		}

//		// 获取request
//		HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
//		// 设置IP地址
//		sysLog.setIp(IPUtils.getIpAddr(request));
//
//		// 获取登录用户信息
//		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//		if (sysUser != null) {
//			sysLog.setUserid(sysUser.getUsername());
//			sysLog.setUsername(sysUser.getRealname());
//
//		}
//		// 耗时
//		sysLog.setCostTime(time);
//		sysLog.setCreateTime(new Date());
		// 保存系统日志
		// sysLogService.save(sysLog);
		Long ss = sysLogService.findTotalVisitCount();
		SysLog entity = new SysLog();
		entity.setPkId(123L);
		sysLogService.save(entity);
		System.out.println("ss=" + ss);
	}
}
