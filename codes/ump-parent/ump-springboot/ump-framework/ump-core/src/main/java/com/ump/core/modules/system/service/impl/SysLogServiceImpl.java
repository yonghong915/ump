package com.ump.core.modules.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ump.commons.constant.ConstantUtil;
import com.ump.core.base.datasource.DS;
import com.ump.core.modules.system.entity.SysLog;
import com.ump.core.modules.system.mapper.SysLogMapper;
import com.ump.core.modules.system.service.ISysLogService;

@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
	@Autowired
	private SysLogMapper sysLogMapper;

	@DS(value=ConstantUtil.DSType.DS_TYPE_SYSDB)
	@Override
	public Long findTotalVisitCount() {
		return sysLogMapper.findTotalVisitCount();
	}
}
