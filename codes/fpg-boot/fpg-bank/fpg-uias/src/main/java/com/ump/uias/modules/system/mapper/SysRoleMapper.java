package com.ump.uias.modules.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ump.uias.modules.system.entity.SysRole;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author scott
 * @since 2018-12-19
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

	List<SysRole> queryRoles(SysRole entity);

}
