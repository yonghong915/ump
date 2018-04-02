package com.ump.cfn.sysmgr.role.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ump.cfn.sysmgr.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Override
	public Set<String> loadRoleIdByUsername(String username) {
		Set<String> set = new HashSet<String>();
		set.add("manager");
		set.add("admin");
		set.add("user");
		return set;
	}

}
