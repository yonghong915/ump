package com.ump.sys.user.entity;

import com.ump.core.base.entity.BaseObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author fangyh
 * @date 2018-09-19 21:26:19
 * @version 1.0.0
 */
public class Permission extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Setter
	@Getter
	private Integer permissionId;

	@Setter
	@Getter
	private String permissionName;
}
