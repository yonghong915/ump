package com.ump.cfn.sysmgr.resource.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.ump.exception.BusinessException;

import com.ump.cfn.sysmgr.resource.dao.ResourceDao;
import com.ump.cfn.sysmgr.resource.model.Resource;
import com.ump.cfn.sysmgr.resource.service.ResourceService;
import com.ump.core.base.service.BaseServiceImpl;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {
	@Autowired
	private ResourceDao resourceDao;

	@Override
	public Set<String> loadPermissionsByUsername(String username) {
		Set<String> set = new HashSet<String>();
		set.add("/backstage/user/findByPage");
		set.add("/backstage/role");
		set.add("/backstage/resource");
		set.add("/findByPage");
		return set;
	}

	@Override
	public List<Resource> findResourcesByUserName(String userCode) {
		try {
			return resourceDao.findResourcesByUserName(userCode);
		} catch (DataAccessException e) {
			throw new BusinessException("数据库删除失败", e);
		}
	}

}
