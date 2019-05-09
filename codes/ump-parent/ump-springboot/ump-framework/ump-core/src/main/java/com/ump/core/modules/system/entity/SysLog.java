package com.ump.core.modules.system.entity;

import com.ump.core.base.entity.BaseEntity;

import lombok.Data;

@Data
public class SysLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String logContent;

	private Integer logType;

	private String method;

	private String requestParam;
}
