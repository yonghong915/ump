package com.ump.core.modules.system.entity;

import com.ump.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户角色表
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 角色id
	 */
	private String roleId;

	public SysUserRole() {
	}

	public SysUserRole(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

}
