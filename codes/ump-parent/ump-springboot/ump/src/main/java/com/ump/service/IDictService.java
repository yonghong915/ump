/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ump.entity.sys.DictEntity;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 10:07:05
 *
 */
public interface IDictService extends IService<DictEntity> {
	/***/
	public IPage<DictEntity> queryDictList(DictEntity entity, int page, int pageSize);
	
	public void saveDict(DictEntity entity);
}
