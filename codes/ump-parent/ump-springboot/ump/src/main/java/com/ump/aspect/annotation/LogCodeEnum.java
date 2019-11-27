/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.aspect.annotation;

import lombok.Getter;
import lombok.Setter;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-27 20:42:44
 *
 */
public enum LogCodeEnum {
	LOGIN("1001", LogTypeEnum.LOGINLOG, "login", "登入");

	@Getter
	@Setter
	private String code;

	@Getter
	@Setter
	private LogTypeEnum logType;

	@Getter
	@Setter
	private String alias;

	@Getter
	@Setter
	private String description;

	LogCodeEnum(String code, LogTypeEnum logType, String alias, String description) {
		this.code = code;
		this.logType = logType;
		this.alias = alias;
		this.description = description;
	}

	public static LogCodeEnum getLogCodeEnumByCode(String code) {
		if (null != code && !"".equals(code.trim())) {
			for (LogCodeEnum enums : values()) {
				if (enums.getCode().equals(code)) {
					return enums;
				}
			}
		}
		return null;
	}
}
