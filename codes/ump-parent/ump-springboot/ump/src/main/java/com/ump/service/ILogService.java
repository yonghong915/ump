/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ump.entity.sys.LogEntity;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 10:07:05
 *
 */
public interface ILogService extends IService<LogEntity> {
	/***/
	public IPage<LogEntity> queryLogList(LogEntity entity, int page, int pageSize);
	
	public void saveLog(LogEntity entity);
}
