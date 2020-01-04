/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.entity.sys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ump.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统日志实体
 * 
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-30 17:59:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "S_LOG")
public class LogEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2180998803025982844L;

	@TableId
	private String logId;

	private String logName;

	private String ipAddress;

	private String logType;

	private String logMoudle;

	private String requestUrl;

	private String requestType;

	private String requestMethod;

	private String requestParams;

	private String result;

	private Long costTime;

	private String remark;
}
