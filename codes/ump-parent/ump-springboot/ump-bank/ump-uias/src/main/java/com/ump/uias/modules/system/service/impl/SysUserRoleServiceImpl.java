package com.ump.uias.modules.system.service.impl;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ump.uias.modules.system.entity.SysRole;
import com.ump.uias.modules.system.entity.SysUserRole;
import com.ump.uias.modules.system.mapper.SysUserRoleMapper;
import com.ump.uias.modules.system.service.ISysUserRoleService;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author scott
 * @since 2018-12-21
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

	//@Autowired
	//private ISysUserService userService;
	
	//@Autowired
	//private ISysRoleService roleService;

	@Autowired
	private SysUserRoleMapper userRoleMapper;

	/**
	 * 查询所有用户对应的角色信息
	 */
	@Override
	public Map<String, String> queryUserRole() {
		Map<String, String> map = new IdentityHashMap<>();

		return map;
	}

	@Override
	public List<SysRole> queryRolesByUserName(String username) {
		return userRoleMapper.queryRolesByUserName(username);
	}

}
