package com.ump.core.common.aspect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author fangyh
 * @version 1.0.0
 * @since 2019-07-13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface PermissionData {
	String value() default "";

	/**
	 * 配置菜单的组件路径,用于数据权限
	 */
	String pageComponent() default "";
}