package com.ump.core.modules.system.entity;

import com.ump.core.base.entity.BaseEntity;

import lombok.Data;

@Data
public class SysPermission extends BaseEntity {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String permissionCode;

	private String permissionName;
}
