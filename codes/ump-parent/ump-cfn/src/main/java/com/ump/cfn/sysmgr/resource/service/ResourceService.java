package com.ump.cfn.sysmgr.resource.service;

import java.util.List;
import java.util.Set;

import com.ump.cfn.sysmgr.resource.model.Resource;
import com.ump.core.base.service.BaseService;

public interface ResourceService extends BaseService<Resource>{

	Set<String> loadPermissionsByUsername(String username);
	public List<Resource> findResourcesByUserName(String userCode);
}
