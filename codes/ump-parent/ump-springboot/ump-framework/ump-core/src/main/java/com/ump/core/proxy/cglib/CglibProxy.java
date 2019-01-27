package com.ump.core.proxy.cglib;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.commons.ReflectUtil;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public Object getInstance(String targetClass) {
		Object targetObj = ReflectUtil.getInstanceObj(targetClass);
		return getInstance(targetObj);
	}

	public Object getInstance(Object target) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		// 回调方法的参数为代理类对象CglibProxy，最后增强目标类调用的是代理类对象CglibProxy中的intercept方法
		enhancer.setCallback(this);
		// 此刻，base不是单纯的目标类，而是增强过的目标类
		return enhancer.create();
	}

	public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// 添加切面逻辑（advise），此处是在目标类代码执行之前，即为MethodBeforeAdviceInterceptor。
		logger.info("before-------------");
		// 执行目标类add方法
		proxy.invokeSuper(object, args);
		// 添加切面逻辑（advise），此处是在目标类代码执行之后，即为MethodAfterAdviceInterceptor。
		logger.info("after--------------");
		return null;
	}
}
