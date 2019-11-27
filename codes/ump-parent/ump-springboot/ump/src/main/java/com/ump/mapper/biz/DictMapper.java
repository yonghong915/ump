/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.mapper.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ump.entity.sys.DictEntity;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 09:35:48
 *
 */
public interface DictMapper extends BaseMapper<DictEntity> {

	/***/
	public List<DictEntity> queryDictList(Page<DictEntity> page, @Param("ew") DictEntity entity);

	public void saveDict(DictEntity entity);
}
