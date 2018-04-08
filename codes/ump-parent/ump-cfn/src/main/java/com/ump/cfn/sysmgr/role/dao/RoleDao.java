package com.ump.cfn.sysmgr.role.dao;

import java.util.List;


import com.ump.cfn.sysmgr.role.model.Role;
import com.ump.core.base.dao.BaseDao;

public interface RoleDao extends BaseDao<Role> {
	public List<Role> findRolesByUserName(String userCode);

}
