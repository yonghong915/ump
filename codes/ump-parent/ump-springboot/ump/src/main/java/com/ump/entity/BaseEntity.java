/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.ump.util.constant.Constants;

import lombok.Data;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 09:44:36
 *
 */
@Data
public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private Long pkId;

	/** 是否删除 */
	private Integer isDel = 0;

	/** 系统编码 */
	private String systemCode = Constants.SYSTEM_CODE;

	/** 创建人员 */
	private Long crtUser;

	/** 创建部门 */
	private Long crtDept;

	/** 创建时间 */
	private Timestamp crtTime;

	/** 修改人员 */
	private Long modUser;

	/** 修改部门 */
	private Long modDept;

	/** 修改时间 */
	private Timestamp modTime;
}
