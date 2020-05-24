package com.ump.commons.desensitization;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author fangyh
 * @date 2018-08-18 21:16:18
 * @version 1.0.0
 */
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Desensitized {
	/* 脱敏类型(规则) */
	SensitiveType type();

	/* 判断注解是否生效的方法 */
	String isEffictiveMethod() default "";
}