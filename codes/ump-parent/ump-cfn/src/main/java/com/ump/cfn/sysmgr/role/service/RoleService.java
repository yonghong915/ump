package com.ump.cfn.sysmgr.role.service;

import java.util.Set;

public interface RoleService {

	Set<String> loadRoleIdByUsername(String username);

}
