package com.ump.core.base.dao;

import java.util.List;

import com.ump.core.base.model.BaseObject;
import com.ump.core.util.page.Page;
import com.ump.exception.DaoException;

public interface BaseDao<T extends BaseObject> {

	/**
	 * 实体保存
	 * 
	 * @param entity
	 * @throws Exception
	 */
	int save(T entity) throws DaoException;

	/**
	 * 实体更新
	 * 
	 * @param entity
	 * @throws Exception
	 */
	int update(T entity) throws DaoException;

	int delete(T entity) throws DaoException;

	int deleteBatch(List<T> list) throws DaoException;

	T queryById(String pid) throws DaoException;

	List<T> find(T entity) throws DaoException;

	List<T> findByPage(T o, Page<T> page) throws DaoException;

	int count(T o) throws DaoException;

	int deleteById(String id) throws DaoException;

	// /**
	// * 根据对应列获取全部实体信息，排序
	// *
	// * @param orderBy
	// * 排序字段
	// * @return
	// */
	// List<E> getAll(String orderBy);
	//
	// /**
	// * 获取全部实体信息
	// *
	// * @return
	// */
	// List<E> getAll();
	//
	// /**
	// * 查询数据
	// *
	// * @param hql
	// * @param parameters
	// * @return
	// */
	// E queryForObject(String hql, Object[] parameters);
	//
	// /**
	// * 根据主键pid 获取对应实体信息
	// *
	// * @param pid
	// * @return
	// */
	// E get(Integer pid);
	//
	// void evict(Object entity);
	//
	// /**
	// * 根据入参参数类型获取到复合条件的所有实体信息条数
	// *
	// * @param condition
	// * @return
	// */
	// // int getRowCountByDetachedCriteria(DetachedCriteria condition);
	// //
	// // /**
	// // * 根据入参参数类型获取到复合条件的所有实体信息list集合
	// // *
	// // * @param condition
	// // * @param page
	// // * @param rows
	// // * @return
	// // */
	// // List<E> findByDetachedCriteria(DetachedCriteria condition, int page, int
	// // rows);
	//
	// /**
	// * 逻辑删除，入参为pid主键数组
	// *
	// * @param pidArray
	// * @throws Exception
	// */
	// void deleteLogicByIds(String[] pidArray) throws Exception;

}
