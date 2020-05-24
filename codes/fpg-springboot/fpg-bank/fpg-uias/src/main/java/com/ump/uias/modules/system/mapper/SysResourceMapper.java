package com.ump.uias.modules.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ump.uias.modules.system.entity.SysResource;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author scott
 * @since 2018-12-19
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

	List<SysResource> queryResourcesByUserName(String username);

}
