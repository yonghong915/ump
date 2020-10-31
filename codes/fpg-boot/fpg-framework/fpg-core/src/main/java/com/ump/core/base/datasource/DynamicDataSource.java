package com.ump.core.base.datasource;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源（需要继承AbstractRoutingDataSource）
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

	@Override
	protected DataSource determineTargetDataSource() {
		return super.determineTargetDataSource();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		if (logger.isInfoEnabled()) {
			logger.info("datasource type is {}.", DataSourceContextHolder.getDataSourceType());
		}
		return DataSourceContextHolder.getDataSourceType();
	}
}
