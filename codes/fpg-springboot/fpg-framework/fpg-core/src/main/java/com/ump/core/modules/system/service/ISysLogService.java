package com.ump.core.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ump.core.modules.system.entity.SysLog;

public interface ISysLogService extends IService<SysLog>{
	public Long findTotalVisitCount() ;
}
