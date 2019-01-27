package com.ump.core.base.batch.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyApplicationContextUtil implements ApplicationContextAware {
	private static ApplicationContext context;

	public static void setContext(ApplicationContext context) {
		MyApplicationContextUtil.context = context;
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public final static Object getBeanObj(String beanName) {
		return context.getBean(beanName);
	}

	public final static Object getBeanObj(String beanName, Class<?> requiredType) {
		return context.getBean(beanName, requiredType);
	}

}
