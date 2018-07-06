package com.ump.core.base.service;

import java.util.List;

import com.ump.core.base.model.BaseObject;
import com.ump.core.util.page.Page;

/**
 * 
 * @author fangyh
 * @date 2017-04-22 12:29:06
 * @version 1.0
 */
public interface BaseService<E extends BaseObject> {

	/**
	 * 保存一个对象
	 * 
	 * @param o
	 *            对象
	 * @return 对象的ID
	 */
	public int save(E entity);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public int update(E entity);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public int delete(E o);

	public int deleteById(String id);

	/**
	 * 批量删除一个对象
	 * 
	 * @param s
	 *            (主键)数组
	 */
	public int deleteBatch(List<E> os);

	public E findById(String pid);

	/**
	 * 获得对象列表
	 * 
	 * @param o
	 *            对象
	 * @return List
	 */
	public List<E> find(E o);

	/**
	 * 获得对象列表
	 * 
	 * @param o
	 *            对象
	 * @param page
	 *            分页对象
	 * @return List
	 */
	public Page<E> findByPage(E o, Page<E> page);

	/**
	 * 统计数目
	 * 
	 * @param o
	 *            对象
	 * @return long
	 */
	public int count(E o);
}
