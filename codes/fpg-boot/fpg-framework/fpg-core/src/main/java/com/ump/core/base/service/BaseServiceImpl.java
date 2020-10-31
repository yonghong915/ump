package com.ump.core.base.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.ump.core.base.dao.BaseDao;
import com.ump.core.base.entity.BaseEntity;

/**
 * 
 * @author fangyh
 * @date 2017-04-25 17:51:36
 * @version 1.0
 * @param <T>
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected BaseDao<T> baseDao;

	@Override
	public int save(T entity) {
		return baseDao.save(entity);

	}

	@Override
	public int update(T entity) {
		return baseDao.update(entity);
	}

	@Override
	public int delete(T entity) {
		return baseDao.delete(entity);
	}

	@Override
	public int deleteBatch(List<T> list) {
		return baseDao.deleteBatch(list);
	}

	@Override
	public T queryById(String pid) {
		return baseDao.queryById(pid);
	}

	@Override
	public List<T> find(T o) {
		return baseDao.find(o);
	}

	@Override
	public int deleteById(String id) {
		return baseDao.deleteById(id);
	}
}
