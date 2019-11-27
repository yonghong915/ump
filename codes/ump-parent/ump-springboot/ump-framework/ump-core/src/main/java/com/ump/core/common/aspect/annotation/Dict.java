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
 * @date 2019-07-16 22:02:04
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {
	/**
	 * 方法描述: 数据code 作 者： dangzhenghui 日 期： 2019年03月17日-下午9:37:16
	 *
	 * @return 返回类型： String
	 */
	String dicCode();

	/**
	 * 方法描述: 数据Text 作 者： dangzhenghui 日 期： 2019年03月17日-下午9:37:16
	 *
	 * @return 返回类型： String
	 */
	String dicText() default "";

	/**
	 * 方法描述: 数据字典表 作 者： dangzhenghui 日 期： 2019年03月17日-下午9:37:16
	 *
	 * @return 返回类型： String
	 */
	String dictTable() default "";
}
