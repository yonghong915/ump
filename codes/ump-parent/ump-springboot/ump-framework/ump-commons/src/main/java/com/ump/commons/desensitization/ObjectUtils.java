package com.ump.commons.desensitization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ObjectUtils {
	private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

	private ObjectUtils() {
	}

	/**
	 * 用序列化-反序列化方式实现深克隆 缺点：1、被拷贝的对象必须要实现序列化
	 *
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deepCloneObject(T obj) {
		T t = (T) new Object();
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(obj);
			out.close();
			ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			ObjectInputStream in = new ObjectInputStream(byteIn);
			t = (T) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			logger.error("clone object err:", e);
		}
		return t;
	}

	/**
	 * 获取包括父类所有的属性
	 *
	 * @param objSource
	 * @return
	 */
	public static Field[] getAllFields(Object objSource) {
		/* 获得当前类的所有属性(private、protected、public) */
		List<Field> fieldList = new ArrayList<>();
		Class<?> tempClass = objSource.getClass();
		while (tempClass != null ) {// 当父类为null的时候说明到达了最上层的父类(Object类).
			fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
			tempClass = tempClass.getSuperclass(); // 得到父类,然后赋给自己
		}
		Field[] fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		return fields;
	}
}
