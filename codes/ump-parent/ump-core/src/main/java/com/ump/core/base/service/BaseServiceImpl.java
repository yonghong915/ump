package com.ump.core.base.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.core.util.page.Page;


/**
 * 
 * @author fangyh
 * @date 2017-04-25 17:51:36
 * @version 1.0
 */
public class BaseServiceImpl<T> implements BaseService<T> {
	protected Logger logger = LoggerFactory.getLogger(getClass());
//	@Autowired
//	protected BaseDao<T> baseDao;
//
//	@Override
//	public int insert(T o) {
//		return baseDao.insert(o);
//	}
//
//	@Override
//	public int delete(T o) {
//		return baseDao.delete(o);
//	}
//
//	@Override
//	public int deleteBatch(List<T> os) {
//		return baseDao.deleteBatch(os);
//	}
//
//	@Override
//	public int update(T o) {
//		return baseDao.update(o);
//	}
//
//	@Override
//	public List<T> find(T o) {
//		return baseDao.find(o);
//	}
//
//	@Override
//	public Page<T> findByPage(T o, Page<T> page) {
//		page.setResults(baseDao.findByPage(o, page));
//		return page;
//	}
//
//	@Override
//	public int count(T o) {
//		return baseDao.count(o);
//	}

	@Override
	public int insert(T o) {
		return 0;
	}

	@Override
	public int delete(T o) {
		return 0;
	}

	@Override
	public int deleteBatch(List<T> os) {
		return 0;
	}

	@Override
	public int update(T o) {
		return 0;
	}

	@Override
	public List<T> find(T o) {
		return null;
	}

	@Override
	public Page<T> findByPage(T o, Page<T> page) {
		return null;
	}

	@Override
	public int count(T o) {
		return 0;
	}

	@Override
	public int deleteById(String id) {
		return 0;
	}
}
