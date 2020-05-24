package com.ump.core.base.dao;

import java.util.List;

import com.ump.core.base.entity.BaseEntity;
import com.ump.core.util.page.Page;

public interface BaseDao<T extends BaseEntity> {

	/**
	 * 实体保存
	 * 
	 * @param entity
	 * @throws Exception
	 */
	int save(T entity);

	/**
	 * 实体更新
	 * 
	 * @param entity
	 * @throws Exception
	 */
	int update(T entity);

	int delete(T entity);

	int deleteBatch(List<T> list);

	T queryById(String pid);

	List<T> find(T entity);

	List<T> findByPage(T o, Page<T> page);

	int count(T o);

	int deleteById(String id);
}
