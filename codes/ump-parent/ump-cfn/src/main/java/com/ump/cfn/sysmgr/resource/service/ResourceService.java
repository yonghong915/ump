package com.ump.cfn.sysmgr.resource.service;

import java.util.Set;

public interface ResourceService {

	Set<String> loadPermissionsByUsername(String username);

}
