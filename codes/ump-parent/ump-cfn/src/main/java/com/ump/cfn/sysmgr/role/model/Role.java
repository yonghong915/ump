package com.ump.cfn.sysmgr.role.model;

import java.util.List;

import com.ump.cfn.sysmgr.permission.model.Permission;
import com.ump.core.base.model.BaseObject;

import lombok.Getter;
import lombok.Setter;

public class Role extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Setter
	@Getter
	private String roleName;

	@Setter
	@Getter
	private List<Permission> permissions;

}
