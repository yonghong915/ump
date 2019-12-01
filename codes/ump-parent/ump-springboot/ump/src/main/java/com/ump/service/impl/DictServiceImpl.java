/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ump.entity.sys.DictEntity;
import com.ump.mapper.sys.DictMapper;
import com.ump.service.IDictService;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 10:07:32
 *
 */
@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictMapper, DictEntity> implements IDictService {

	/***/
//	public List<DictEntity> queryDictList(DictEntity entity) {
//		return this.baseMapper.queryDictList(entity);
//	}

	public IPage<DictEntity> queryDictList(DictEntity entity, int page, int pageSize) {
		Page<DictEntity> p = new Page<>(page, pageSize);
		p.setRecords(this.baseMapper.queryDictList(p, entity));
		return p;
	}

	@Override
	public void saveDict(DictEntity entity) {
		this.baseMapper.insert(entity);
	}

}
