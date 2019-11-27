package com.ump.uias.config.mybatis;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import com.ump.core.base.entity.CreateOnFuncation;

import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * mybatis拦截器，自动注入创建人、创建时间、修改人、修改时间
 * 
 * @author scott
 * @date 2019-01-19
 *
 */
@Slf4j
@Component
@Accessors(chain = true)
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		String sqlId = mappedStatement.getId();
		log.debug("------sqlId------" + sqlId);

		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		Object parameter = invocation.getArgs()[1];
		log.debug("------sqlCommandType------" + sqlCommandType);

		if (parameter == null) {
			return invocation.proceed();
		}
		List<Field> fields = getAllFields(parameter.getClass());
		// Field[] fields = parameter.getClass().getFields();
		for (Field field : fields) {
			log.debug("------field.name------" + field.getName());
			if (field.isAnnotationPresent(CreateOnFuncation.class)) {

				AnnotatedType at = field.getAnnotatedType();
				Class<?> type = field.getType();

				if (SqlCommandType.INSERT == sqlCommandType) {
					if (type == String.class) {
						field.setAccessible(true);
						field.set(parameter, new Random().nextInt());
						field.setAccessible(false);
					} else if (type == Timestamp.class) {
						field.setAccessible(true);
						Timestamp ts = new Timestamp(System.currentTimeMillis());  
						field.set(parameter, ts);
						field.setAccessible(false);
					}
				}
			}

//		if (SqlCommandType.INSERT == sqlCommandType) {
//			Field[] fields = parameter.getClass().getFields();
//			for (Field field : fields) {
//				log.debug("------field.name------" + field.getName());
//				if(field.isAnnotationPresent(CreateOnFuncation.class)) {
//					
//					AnnotatedType at = field.getAnnotatedType();
//					Class<?> type = field.getType();
//					if(type == String.class) {
//						System.out.println("aaaaa");
//					}
//					
//					if(SqlCommandType.INSERT == sqlCommandType) {
//						field.setAccessible(true);
//						//field.set(parameter, value);
//					}
//				}

//				try {
//					if ("createBy".equals(field.getName())) {
//						field.setAccessible(true);
//						Object local_createBy = field.get(parameter);
//						field.setAccessible(false);
//						if (local_createBy == null || local_createBy.equals("")) {
//							String createBy = "jeecg-boot";
//							// 获取登录用户信息
//							SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//							if (sysUser != null) {
//								// 登录账号
//								createBy = sysUser.getUserName();
//							}
//							if (!isEmpty(createBy)) {
//								field.setAccessible(true);
//								field.set(parameter, createBy);
//								field.setAccessible(false);
//							}
//						}
//					}
//					// 注入创建时间
//					if ("createTime".equals(field.getName())) {
//						field.setAccessible(true);
//						Object local_createDate = field.get(parameter);
//						field.setAccessible(false);
//						if (local_createDate == null || local_createDate.equals("")) {
//							field.setAccessible(true);
//							field.set(parameter, new Date());
//							field.setAccessible(false);
//						}
//					}
//				} catch (Exception e) {
//				}
//			}
//		}
//		if (SqlCommandType.UPDATE == sqlCommandType) {
//			Field[] fields = null;
//			if (parameter instanceof ParamMap) {
//				ParamMap<?> p = (ParamMap<?>) parameter;
//				parameter = p.get("param1");
//				fields = parameter.getClass().getDeclaredFields();
//			} else {
//				fields = parameter.getClass().getDeclaredFields();
//			}
//
//			for (Field field : fields) {
//				log.debug("------field.name------" + field.getName());
//				try {
//					if ("updateBy".equals(field.getName())) {
//						field.setAccessible(true);
//						Object local_updateBy = field.get(parameter);
//						field.setAccessible(false);
//						if (local_updateBy == null || local_updateBy.equals("")) {
//							String updateBy = "jeecg-boot";
//							// 获取登录用户信息
//							SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//							if (sysUser != null) {
//								// 登录账号
//								updateBy = sysUser.getUserName();
//							}
//							if (!isEmpty(updateBy)) {
//								field.setAccessible(true);
//								field.set(parameter, updateBy);
//								field.setAccessible(false);
//							}
//						}
//					}
//					if ("updateTime".equals(field.getName())) {
//						field.setAccessible(true);
//						Object local_updateDate = field.get(parameter);
//						field.setAccessible(false);
//						if (local_updateDate == null || local_updateDate.equals("")) {
//							field.setAccessible(true);
//							field.set(parameter, new Date());
//							field.setAccessible(false);
//						}
//					}
//				} catch (Exception e) {
//				}
//			}
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
