package com.ump.core.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ump.core.modules.system.entity.SysResource;
import com.ump.core.modules.system.mapper.SysResourceMapper;
import com.ump.core.modules.system.service.ISysResourceService;

@CacheConfig(cacheNames = { "umpResourceCache" })
@Service("resourceService")
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

	@Autowired
	private SysResourceMapper resourceMapper;

	@Override
	public List<SysResource> queryResourcesByUserName(String username) {
		return resourceMapper.queryResourcesByUserName(username);
	}

}
