/*
 * Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.common.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @Time 2019-07-13 19:10:06
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {
	/**
	 * 是否非空
	 */
	public boolean notNull() default false;

	/**
	 * 是否为数值
	 */
	public boolean numeric() default false;

	/**
	 * 最大长度
	 */
	public int maxLen() default -1;

	/**
	 * 最小长度
	 */
	public int minLen() default -1;

	/**
	 * 最小数值
	 */
	public long minNum() default -999999;
}
