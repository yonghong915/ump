/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.common.log;

import lombok.Getter;
import lombok.Setter;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-27 20:44:10
 *
 */
public enum LogTypeEnum {
	AUDITLOG("01", "审计日志"), SYSLOG("02", "系统日志"), BUSSLOG("03", "业务日志"), LOGINLOG("04", "登录日志"), EXITLOG("05", "退出日志");

	@Getter
	@Setter
	private String code;

	@Getter
	@Setter
	private String description;

	LogTypeEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public static LogTypeEnum getLogTypeEnumByCode(String code) {
		if (null != code && !"".equals(code.trim())) {
			for (LogTypeEnum enums : values()) {
				if (enums.getCode().equals(code)) {
					return enums;
				}
			}
		}
		return null;
	}
}
