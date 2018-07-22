package com.ump.commons;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.exception.BusinessException;

/**
 * 利用反射进行操作的一个工具类
 */
public class ReflectUtil {
	private static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

	private ReflectUtil() {
	}

	public static Object getInstanceObj(String className) {
		if (CommUtils.isEmpty(className)) {
			return null;
		}
		try {
			return Class.forName(className).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new BusinessException("Reflect Object error:", e);
		}
	}

	/**
	 * 利用反射获取指定对象的指定属性
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @return 目标属性的值
	 */
	public static Object getFieldValue(Object obj, String fieldName) {
		Object result = null;
		Field field = ReflectUtil.getField(obj, fieldName);
		if (field != null) {
			field.setAccessible(true);
			try {
				result = field.get(obj);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("error={}", e);
			}
		}
		return result;
	}

	/**
	 * 利用反射获取指定对象里面的指定属性
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @return 目标字段
	 */
	private static Field getField(Object obj, String fieldName) {
		Field field = null;
		for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
				break;
			} catch (NoSuchFieldException e) {
				// 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
			}
		}
		return field;
	}

	/**
	 * 利用反射设置指定对象的指定属性为指定的值
	 * 
	 * @param obj
	 *            目标对象
	 * @param fieldName
	 *            目标属性
	 * @param fieldValue
	 *            目标值
	 */
	public static void setFieldValue(Object obj, String fieldName, String fieldValue) {
		Field field = ReflectUtil.getField(obj, fieldName);
		if (field != null) {
			try {
				field.setAccessible(true);
				field.set(obj, fieldValue);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("error={}", e);
			}
		}
	}

	/**
	 * 两者属性名一致时，拷贝source里的属性到dest里
	 * 
	 * @return void
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyPorperties(Object dest, Object source)
			throws IllegalAccessException, InvocationTargetException {
		Class<?> srcCla = source.getClass();
		Field[] fsF = srcCla.getDeclaredFields();

		for (Field s : fsF) {
			String name = s.getName();
			Object srcObj = invokeGetterMethod(source, name);
			try {
				BeanUtils.setProperty(dest, name, srcObj);
			} catch (Exception e) {
				logger.error("error={}", e);
			}

		}
	}

	/**
	 * 调用Getter方法.
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object invokeGetterMethod(Object target, String propertyName)
			throws IllegalAccessException, InvocationTargetException {
		String getterMethodName = "get" + StringUtils.capitalize(propertyName);
		return invokeMethod(target, getterMethodName, new Class[] {}, new Object[] {});
	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符.
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes,
			final Object[] parameters) throws IllegalAccessException, InvocationTargetException {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] parameterType "
					+ parameterTypes + " on target [" + object + "]");
		}

		method.setAccessible(true);
		return method.invoke(object, parameters);
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod.
	 * 
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {// NOSONAR
												// Method不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 
	 * @param className
	 * @return
	 */
	public static Object getObjectInstance(String className) {
		if (CommUtils.isEmpty(className)) {
			return null;
		}
		try {
			Class<?> clazz = Class.forName(className);
			return clazz.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logger.error("obtain object instance error:{}", e);
		}
		return null;
	}

	/**
	 * 
	 * @param className
	 * @return
	 */
	public static Object getObjectInstance(String className, Map<String, Object> paramMap) {
		if (CommUtils.isEmpty(className)) {
			return null;
		}
		if (!(paramMap instanceof Map)) {
			throw new IllegalArgumentException("illeagal argument,should be map type.");
		}
		try {
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getConstructor(Map.class);
			return constructor.newInstance(paramMap);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | InvocationTargetException e) {
			logger.error("error={}", e);
		}
		return null;
	}
}
