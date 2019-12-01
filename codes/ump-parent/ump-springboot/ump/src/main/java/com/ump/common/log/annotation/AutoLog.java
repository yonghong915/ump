/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.common.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ump.common.log.LogCodeEnum;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-27 20:15:23
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AutoLog {
	LogCodeEnum logCode();

	String remark() default "";
}
