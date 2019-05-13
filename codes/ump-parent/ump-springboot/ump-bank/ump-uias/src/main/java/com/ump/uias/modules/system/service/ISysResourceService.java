package com.ump.uias.modules.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ump.uias.modules.system.entity.SysResource;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author scott
 * @since 2018-12-19
 */
public interface ISysResourceService extends IService<SysResource> {

	List<SysResource> queryResourcesByUserName(String username);

}