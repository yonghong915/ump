package com.ump.sys.user.entity;

import java.util.Objects;

import com.ump.core.base.entity.BaseObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author fangyh
 * @date 2018-08-09 21:42:09
 * @version 1.0.0
 */
public class Role extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/***/
	@Setter
	@Getter
	private String roleId;

	/***/
	@Setter
	@Getter
	private String roleName;

	/***/
	@Setter
	@Getter
	private String roleDesc;

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Role)) {
			return false;
		}
		Role role = (Role) o;
		return Objects.equals(roleId, role.roleId) && Objects.equals(roleName, role.roleName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleId, roleName);
	}
}
