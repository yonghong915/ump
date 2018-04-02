package com.ump.cfn.sysmgr.resource.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ump.cfn.sysmgr.resource.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	@Override
	public Set<String> loadPermissionsByUsername(String username) {
		Set<String> set = new HashSet<String>();
		set.add("/backstage/user/findByPage");
		set.add("/backstage/role");
		set.add("/backstage/resource");
		set.add("/findByPage");
		return set;
	}

}
