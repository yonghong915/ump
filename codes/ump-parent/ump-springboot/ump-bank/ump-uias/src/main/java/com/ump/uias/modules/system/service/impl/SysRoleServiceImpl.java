package com.ump.uias.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ump.uias.modules.system.entity.SysRole;
import com.ump.uias.modules.system.entity.SysUserRole;
import com.ump.uias.modules.system.mapper.SysRoleMapper;
import com.ump.uias.modules.system.service.ISysRoleService;

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
