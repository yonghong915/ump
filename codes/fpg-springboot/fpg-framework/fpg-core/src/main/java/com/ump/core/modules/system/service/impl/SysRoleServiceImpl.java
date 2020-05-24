package com.ump.core.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ump.core.modules.system.entity.SysRole;
import com.ump.core.modules.system.mapper.SysRoleMapper;
import com.ump.core.modules.system.service.ISysRoleService;

@CacheConfig(cacheNames = { "umpRoleCache" })
@Service("roleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

	@Autowired
	private SysRoleMapper roleMapper;

	@Override
	public List<SysRole> queryRoles(SysRole entity) {
		return roleMapper.queryRoles(entity);
	}

}
