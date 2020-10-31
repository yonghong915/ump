package com.ump.core.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ump.core.modules.system.entity.SysLog;

/**
 * <p>
 * 系统日志表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2018-12-26
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

	/**
	 * @功能：清空所有日志记录
	 */
	public void removeAll();

	/**
	 * 获取系统总访问次数
	 *
	 * @return Long
	 */
	
	Long findTotalVisitCount();

	/**
	 * 获取系统今日访问次数
	 *
	 * @return Long
	 */
	Long findTodayVisitCount();

	/**
	 * 获取系统今日访问 IP数
	 *
	 * @return Long
	 */
	Long findTodayIp();

}
