/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ump.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 09:12:18
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "S_USER")
public class UserEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;

	private String userCode;

	private String userName;
	
	private String userPwd;
}
