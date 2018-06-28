package com.ump.cfn.sysmgr.permission.model;

import com.ump.core.base.model.BaseObject;

import lombok.Getter;
import lombok.Setter;

public class Permission extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String permissionId;

	@Getter
	@Setter
	private String permissionName;

	@Getter
	@Setter
	private String permissionDesc;
}
