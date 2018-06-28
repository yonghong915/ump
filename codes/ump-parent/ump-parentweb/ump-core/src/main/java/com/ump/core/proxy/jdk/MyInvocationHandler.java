package com.ump.core.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

	/** 代理的真实对象 */
	private Object target;

	MyInvocationHandler() {
		super();
	}

	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	public Object getInstance() {
		// Proxy为InvocationHandler实现类动态创建一个符合某一接口的代理实例
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	/**
	 * 责集中处理动态代理类上的所有方法调用。 调用处理器根据这三个参数进行预处理或分派到委托类实例上反射执行
	 * 
	 * @param proxy
	 *            代理类实例
	 * @param method
	 *            被调用的方法对象
	 * @param args
	 *            调用参数
	 * @return
	 * @throws Throwable
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 程序执行前加入逻辑，MethodBeforeAdviceInterceptor
		System.out.println("before-----------------------------");
		// 程序执行
		Object result = method.invoke(target, args);
		// 程序执行后加入逻辑，MethodAfterAdviceInterceptor
		System.out.println("after------------------------------");
		return result;
	}

}
