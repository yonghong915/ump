package com.ump.core.base.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ump.commons.exception.BusinessException;
import com.ump.commons.exception.DaoException;
import com.ump.commons.exception.ServiceException;
import com.ump.core.base.dao.BaseDao;
import com.ump.core.base.model.BaseObject;

/**
 * 
 * @author fangyh
 * @date 2017-04-25 17:51:36
 * @version 1.0
 * @param <T>
 */
public abstract class BaseServiceImpl<T extends BaseObject> implements BaseService<T> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected BaseDao<T> baseDao;

	@Override
	public int save(T entity) throws ServiceException {
		try {
			return baseDao.save(entity);
		} catch (DaoException e) {
			throw new ServiceException("数据库保存失败", e);
		}
	}

	@Override
	public int update(T entity) throws ServiceException {
		try {
			return baseDao.update(entity);
		} catch (DaoException e) {
			throw new ServiceException("数据库更新失败", e);
		}
	}

	@Override
	public int delete(T entity) throws ServiceException {
		try {
			return baseDao.delete(entity);
		} catch (DaoException e) {
			throw new ServiceException("数据库删除失败", e);
		}
	}

	@Override
	public int deleteBatch(List<T> list) throws ServiceException {
		try {
			return baseDao.deleteBatch(list);
		} catch (DaoException e) {
			throw new ServiceException("数据库删除失败", e);
		}
	}

	@Override
	public T queryById(String pid) throws BusinessException {
		try {
			return baseDao.queryById(pid);
		} catch (DaoException e) {
			throw new BusinessException("数据库查询失败", e);
		}
	}

	@Override
	public List<T> find(T o) throws ServiceException {
		try {
			return baseDao.find(o);
		} catch (DaoException e) {
			throw new ServiceException("数据库删除失败", e);
		}
	}

	// @Override
	// public Page<T> findByPage(E o, Page<E> page) throws ServiceException {
	// try {
	// page.setResults(baseDao.findByPage(o, page));
	// return page;
	// } catch (DaoException e) {
	// throw new ServiceException("数据库删除失败", e);
	// }
	// }
	//
	// @Override
	// public int count(E o) throws ServiceException {
	// try {
	// return baseDao.count(o);
	// } catch (DaoException e) {
	// throw new ServiceException("数据库删除失败", e);
	// }
	// }

	@Override
	public int deleteById(String id) throws ServiceException {
		try {
			return baseDao.deleteById(id);
		} catch (DaoException e) {
			throw new ServiceException("数据库删除失败", e);
		}
	}
}
