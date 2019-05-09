package com.ump.uias.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ump.uias.modules.system.entity.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser> {
	List<SysUser> queryUserList(@Param("ew") SysUser entity);
}
