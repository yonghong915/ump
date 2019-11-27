package com.ump.core.modules.system.entity;

import java.util.List;

import com.ump.core.base.entity.BaseEntity;

import lombok.Data;

@Data
public class SysRole extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String roleCode;

	private String roleName;

	private List<SysPermission> permissions;
}
