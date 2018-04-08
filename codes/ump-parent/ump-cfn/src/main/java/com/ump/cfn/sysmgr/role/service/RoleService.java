package com.ump.cfn.sysmgr.role.service;

import java.util.List;
import java.util.Set;

import org.ump.exception.BusinessException;

import com.ump.cfn.sysmgr.role.model.Role;
import com.ump.core.base.service.BaseService;

public interface RoleService extends BaseService<Role> {

	Set<String> loadRoleIdByUsername(String username);

	public List<Role> findRolesByUserName(String userCode) throws BusinessException;
}
