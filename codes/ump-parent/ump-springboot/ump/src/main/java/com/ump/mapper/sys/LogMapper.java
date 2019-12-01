/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ump.entity.sys.LogEntity;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 09:35:48
 *
 */
public interface LogMapper extends BaseMapper<LogEntity> {

	/***/
	public List<LogEntity> queryLogList(Page<LogEntity> page, @Param("ew") LogEntity entity);

}
