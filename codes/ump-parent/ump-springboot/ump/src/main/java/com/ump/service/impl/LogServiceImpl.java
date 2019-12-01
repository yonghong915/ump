/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ump.entity.sys.LogEntity;
import com.ump.mapper.sys.LogMapper;
import com.ump.service.ILogService;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 10:07:32
 *
 */
@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements ILogService {

	public IPage<LogEntity> queryLogList(LogEntity entity, int page, int pageSize) {
		Page<LogEntity> p = new Page<>(page, pageSize);
		p.setRecords(this.baseMapper.queryLogList(p, entity));
		return p;
	}

	@Override
	public void saveLog(LogEntity entity) {
		this.baseMapper.insert(entity);
	}
}
