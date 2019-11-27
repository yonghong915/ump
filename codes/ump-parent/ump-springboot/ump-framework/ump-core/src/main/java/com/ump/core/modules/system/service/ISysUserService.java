package com.ump.core.modules.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ump.core.modules.system.entity.SysResource;
import com.ump.core.modules.system.entity.SysRole;
import com.ump.core.modules.system.entity.SysUser;

/**
 * User Service
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
public interface ISysUserService extends IService<SysUser> {

	List<SysUser> selectAll();

	SysUser queryUserByUserName(String userName);

	List<SysRole> queryRolesByUserName(String username);

	List<SysResource> queryResourcesByUserName(String username);
}
