/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.interceptor.mybatis;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 19:38:27
 *
 */
@Component
@Accessors(chain = true)
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisInterceptor implements Interceptor {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		String sqlId = mappedStatement.getId();
		logger.debug("------sqlId------" + sqlId);

		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		Object parameter = invocation.getArgs()[1];
		logger.debug("------sqlCommandType------" + sqlCommandType);

		if (parameter == null) {
			return invocation.proceed();
		}
		List<Field> fields = getAllFields(parameter.getClass());
		// Field[] fields = parameter.getClass().getFields();
		Object value = null;
		for (Field field : fields) {
			if ("serialVersionUID".equals(field.getName())) {
				continue;
			}
			// log.debug("------field.name------" + field.getName());
			if (SqlCommandType.INSERT == sqlCommandType) {
				field.setAccessible(true);
				if ("crtTime".equals(field.getName()) || "modTime".equals(field.getName())) {
					value = new Timestamp(System.currentTimeMillis());
				} else if ("crtUser".equals(field.getName())) {
					value = 12345L;
				} else if ("crtDept".equals(field.getName())) {
					value = 56789L;
				} else if ("modUser".equals(field.getName())) {
					value = 12345L;
				} else if ("modDept".equals(field.getName())) {
					value = 56789L;
				} else {
					value = field.get(parameter);
				}

				field.set(parameter, value);
				field.setAccessible(false);
			} else if (SqlCommandType.UPDATE == sqlCommandType) {
				field.setAccessible(true);
				if ("modTime".equals(field.getName())) {
					value = new Timestamp(System.currentTimeMillis());
				} else if ("modUser".equals(field.getName())) {
					value = 12345L;
				} else if ("modDept".equals(field.getName())) {
					value = 56789L;
				} else {
					value = field.get(parameter);
				}
				field.set(parameter, value);
				field.setAccessible(false);
			}

		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
	}

	public static boolean isEmpty(Object object) {
		if (object == null) {
			return (true);
		}
		if ("".equals(object)) {
			return (true);
		}
		if ("null".equals(object)) {
			return (true);
		}
		return (false);
	}

	public List<Field> getAllFields(Class tempClass) {
		List<Field> fieldList = new ArrayList<>();
		while (tempClass != null) {// 当父类为null的时候说明到达了最上层的父类(Object类).
			fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
			tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
		}
		return fieldList;
	}
}
