package com.ump.core.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author fangyh
 * @date 2018-09-19 14:06:19
 * @version 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Token {
	/**
	 * 
	 * @return
	 */
	boolean save() default false;

	/**
	 * 
	 * @return
	 */
	boolean remove() default false;
}
