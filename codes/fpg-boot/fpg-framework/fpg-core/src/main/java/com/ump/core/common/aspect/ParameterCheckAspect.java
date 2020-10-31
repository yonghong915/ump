package com.ump.core.common.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ump.commons.exception.ParameterException;
import com.ump.core.common.aspect.annotation.Check;
import com.ump.core.common.aspect.annotation.Valid;

/**
 * 
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-07-16 22:02:56
 */
@Component
@Aspect
public class ParameterCheckAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());

	// 定义切点
	@Pointcut("within(com.ump.**.controller..*)")
	public void check() {
	}

	/**
	 * 切面方法，使用统一异常处理
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "check()", argNames = "Valid")
	public Object checkIsValid(ProceedingJoinPoint joinPoint) throws Throwable {
		Object object = null;
		// 参数校验，未抛出异常表示验证OK
		checkValid(joinPoint);
		object = ((ProceedingJoinPoint) joinPoint).proceed();
		return object;
	}

	public void checkValid(ProceedingJoinPoint joinPoint) throws Exception {
		Object[] args = null;
		Method method = null;
		Object target = null;
		String methodName = null;
		String str = "";
		try {
			methodName = joinPoint.getSignature().getName();
			target = joinPoint.getTarget();
			method = getMethodByClassAndName(target.getClass(), methodName);
			Annotation[][] annotations = method.getParameterAnnotations();
			args = joinPoint.getArgs(); // 方法的参数
			if (annotations != null) {
				for (int i = 0; i < annotations.length; i++) {
					Annotation[] anno = annotations[i];
					for (int j = 0; j < anno.length; j++) {
						if (annotations[i][j].annotationType().equals(Valid.class)) {
							str = checkParam(args[i]);
							if (StringUtils.hasText(str)) {
								throw new ParameterException(str);
							}
						}
					}
				}
			}
		} catch (Throwable e) {
			logger.error("参数校验异常" + e);
			throw new ParameterException(str);
		}
	}

	/**
	 * 校验参数
	 * 
	 * @param args
	 * @return
	 * @throws Exception
	 */
	private String checkParam(Object args) throws Exception {
		String retStr = "";
		Field[] field = args.getClass().getDeclaredFields();// 获取方法参数（实体）的field
		for (int j = 0; j < field.length; j++) {
			Check check = field[j].getAnnotation(Check.class);// 获取方法参数（实体）的field上的注解Check
			if (check != null) {
				String str = validateFiled(check, field[j], args);
				if (StringUtils.hasText(str)) {
					retStr = str;
					return retStr;
				}
			}
		}
		return retStr;
	}

	/**
	 * 校验参数规则
	 * 
	 * @param check
	 * @param field
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String validateFiled(Check check, Field field, Object args) throws Exception {
		field.setAccessible(true);
		// 获取field长度
		int length = 0;
		if (field.get(args) != null) {
			length = (String.valueOf(field.get(args))).length();
		}
		if (check.notNull()) {
			if (field.get(args) == null || "".equals(String.valueOf(field.get(args)))) {
				return field.getName() + "不能为空";
			}
		}
		if (check.maxLen() > 0 && (length > check.maxLen())) {
			return field.getName() + "长度不能大于" + check.maxLen();
		}

		if (check.minLen() > 0 && (length < check.minLen())) {
			return field.getName() + "长度不能小于" + check.minLen();
		}

		if (check.numeric() && field.get(args) != null) {
			try {
				new BigDecimal(String.valueOf(field.get(args)));
			} catch (Exception e) {
				return field.getName() + "必须为数值型";
			}
		}
		if (check.minNum() != -999999) {
			try {
				long fieldValue = Long.parseLong(String.valueOf(field.get(args)));
				if (fieldValue < check.minNum()) {
					return field.getName() + "必须不小于" + check.minNum();
				}
			} catch (Exception e) {
				return field.getName() + "必须为数值型，且不小于" + check.minNum();
			}
		}
		return "";
	}

	/**
	 * 根据类和方法名得到方法
	 */
	@SuppressWarnings("rawtypes")
	public Method getMethodByClassAndName(Class c, String methodName) throws Exception {
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}

}
