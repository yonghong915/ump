package com.ump.cfn.sysmgr.role.service;

import java.util.Set;

import com.ump.cfn.sysmgr.role.model.Role;
import com.ump.core.base.service.BaseService;

public interface RoleService extends BaseService<Role>{

	Set<String> loadRoleIdByUsername(String username);

}
