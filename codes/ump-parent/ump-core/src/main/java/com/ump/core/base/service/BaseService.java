package com.ump.core.base.service;

import java.util.List;

import com.ump.core.util.page.Page;


/**
 * 
 * @author fangyh
 * @date 2017-04-22 12:29:06
 * @version 1.0
 */
public interface BaseService<T> {
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 *            对象
	 * @return 对象的ID
	 */
	public int insert(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public int delete(T o);
	
	
	public int deleteById(String id);

	/**
	 * 批量删除一个对象
	 * 
	 * @param s
	 *            (主键)数组
	 */
	public int deleteBatch(List<T> os);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public int update(T o);

	/**
	 * 获得对象列表
	 * 
	 * @param o
	 *            对象
	 * @return List
	 */
	public List<T> find(T o);

	/**
	 * 获得对象列表
	 * 
	 * @param o
	 *            对象
	 * @param page
	 *            分页对象
	 * @return List
	 */
	public Page<T> findByPage(T o, Page<T> page);

	/**
	 * 统计数目
	 * 
	 * @param o
	 *            对象
	 * @return long
	 */
	public int count(T o);
}
