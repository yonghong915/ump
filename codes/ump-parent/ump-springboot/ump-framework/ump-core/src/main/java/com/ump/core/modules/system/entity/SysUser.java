package com.ump.core.modules.system.entity;

import com.ump.core.base.entity.BaseEntity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
public class SysUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	private String realName;

	private String userPwd;

	private String userDesc;

	/***/
	private String userStatus;

}
