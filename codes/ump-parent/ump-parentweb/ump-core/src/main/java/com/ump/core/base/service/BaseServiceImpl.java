package com.ump.core.base.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.ump.exception.BusinessException;
import org.ump.exception.DaoException;
import org.ump.exception.ServiceException;

import com.ump.core.base.dao.BaseDao;
import com.ump.core.base.model.BaseObject;
import com.ump.core.util.page.Page;

/**
 * 
 * @author fangyh
 * @date 2017-04-25 17:51:36
 * @version 1.0
 */
public abstract class BaseServiceImpl<E extends BaseObject> implements BaseService<E> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected BaseDao<E> baseDao;

	@Override
	public int save(E entity) throws ServiceException {
		try {
			return baseDao.save(entity);
		} catch (DaoException e) {
			throw new ServiceException("数据库保存失败", e);
		}
	}

	@Override
	public int update(E entity) throws ServiceException {
		try {
			return baseDao.update(entity);
		} catch (DaoException e) {
			throw new ServiceException("数据库更新失败", e);
		}
	}

	@Override
	public int delete(E entity) throws ServiceException {
		try {
			return baseDao.delete(entity);
		} catch (DaoException e) {
			throw new ServiceException("数据库删除失败", e);
		}
	}

	@Override
	public int deleteBatch(List<E> list) throws ServiceException {
		try {
			return baseDao.deleteBatch(list);
		} catch (DaoException e) {
			throw new ServiceException("数据库删除失败", e);
		}
	}

	@Override
	public E findById(String pid) throws BusinessException {
		try {
			return baseDao.findById(pid);
		} catch (DataAccessException e) {
			throw new BusinessException("数据库查询失败", e);
		}
	}

	@Override
	public List<E> find(E o) throws ServiceException {
		try {
			return baseDao.find(o);
		} catch (DaoException e) {
			throw new ServiceException("数据库删除失败", e);
		}
	}

	@Override
	public Page<E> findByPage(E o, Page<E> page) throws ServiceException {
		try {
			page.setResults(baseDao.findByPage(o, page));
			return page;
		} catch (DaoException e) {
			throw new ServiceException("数据库删除失败", e);
		}
	}

	@Override
	public int count(E o) throws ServiceException {
		try {
			return baseDao.count(o);
		} catch (DaoException e) {
			throw new ServiceException("数据库删除失败", e);
		}
	}

	@Override
	public int deleteById(String id) throws ServiceException {
		try {
			return baseDao.deleteById(id);
		} catch (DaoException e) {
			throw new ServiceException("数据库删除失败", e);
		}
	}
}
