package com.ump.cfn.sysmgr.role.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.ump.exception.BusinessException;

import com.ump.cfn.sysmgr.role.dao.RoleDao;
import com.ump.cfn.sysmgr.role.model.Role;
import com.ump.cfn.sysmgr.role.service.RoleService;
import com.ump.core.base.service.BaseServiceImpl;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	@Autowired
	RoleDao roleDao;

	@Override
	public Set<String> loadRoleIdByUsername(String username) {
		Set<String> set = new HashSet<String>();
		set.add("manager");
		set.add("admin");
		set.add("user");
		return set;
	}

	@Override
	public Role findById(String pid) throws BusinessException {
		try {
			return roleDao.findById(pid);
		} catch (DataAccessException e) {
			throw new BusinessException("数据库删除失败", e);
		}
	}
}
