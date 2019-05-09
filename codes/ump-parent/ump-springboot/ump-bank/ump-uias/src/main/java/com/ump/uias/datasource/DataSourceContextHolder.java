package com.ump.uias.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 构建一个DatabaseType容器，并提供了向其中设置和获取DatabaseType的方法
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
public class DataSourceContextHolder {
	private static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);
	private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();
	public static final String DEFAULT_DS = DatabaseType.UMPDB.name();
	public static final String DATASOURCE_UMPDB = "UMPDB";
	public static final String DATASOURCE_JEECG = "JEECG";

	public static void setDatabaseType(DatabaseType dbType) {
		if (logger.isInfoEnabled()) {
			logger.info("switch datasource to {}", dbType.name());
		}
		contextHolder.set(dbType);
	}

	public static DatabaseType getDatabaseType() {
		return contextHolder.get();
	}

	public static void clearDB() {
		contextHolder.remove();
	}
}
