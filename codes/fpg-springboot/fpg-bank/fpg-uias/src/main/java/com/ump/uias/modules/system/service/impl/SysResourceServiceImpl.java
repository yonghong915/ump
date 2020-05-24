package com.ump.uias.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ump.uias.modules.system.entity.SysResource;
import com.ump.uias.modules.system.mapper.SysResourceMapper;
import com.ump.uias.modules.system.service.ISysResourceService;

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
