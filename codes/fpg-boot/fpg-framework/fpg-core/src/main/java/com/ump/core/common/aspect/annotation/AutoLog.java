package com.ump.core.common.aspect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.ump.commons.constant.ConstantUtil;

/**
 * 
 * @author fangyh
 * @version 1.0.0
 * @since 2019-07-13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLog {
	/**
	 * 
	 * @return
	 */
	String value() default "";

	/**
	 * 日志类型
	 * 
	 * @return
	 */
	int logType() default ConstantUtil.LogType.OPERATE_TYPE;
}
