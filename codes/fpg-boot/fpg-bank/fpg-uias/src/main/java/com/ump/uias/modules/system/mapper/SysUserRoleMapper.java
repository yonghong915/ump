package com.ump.uias.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ump.uias.modules.system.entity.SysRole;
import com.ump.uias.modules.system.entity.SysUserRole;

/**
 * 用户角色表 Mapper 接口
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	@Select("select role_code from sys_role where id in (select role_id from sys_user_role where user_id = (select id from sys_user where username=#{username}))")
	List<String> getRoleByUserName(@Param("username") String username);

	List<SysRole> queryRolesByUserName(@Param("username") String username);
}
