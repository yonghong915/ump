package com.ump.core.modules.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ump.core.modules.system.entity.SysRole;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author scott
 * @since 2018-12-19
 */
public interface ISysRoleService extends IService<SysRole> {

	List<SysRole> queryRoles(SysRole entity);

}
