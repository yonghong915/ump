package com.ump.core.base.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.ump.commons.constant.ConstantUtil;

@Configuration
public class DataSourceConfig {
	private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	@Bean(name = "masterDataSource")
	@Qualifier("masterDataSource")
	@Primary
	public DataSource masterDataSource() {
		logger.info("默认数据库连接池创建中.......");
		DataSourceProperties dsProps = dataSourceProperties4Master();
		return getDataSource(dsProps);
	}

	private DataSource getDataSource(DataSourceProperties dsProps) {
		DataSourceBuilder<?> dsBuilder = DataSourceBuilder.create();
		// TODO
		// 后续对密码加密解密
		String password = dsProps.getPassword();
		// TODO
		dsBuilder.password(password);
		dsBuilder.username(dsProps.getUsername());
		dsBuilder.driverClassName(dsProps.getDriverClassName());
		dsBuilder.url(dsProps.getUrl());
		Class<? extends DataSource> ds = dsProps.getType();
		dsBuilder.type(ds);
		return dsBuilder.build();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSourceProperties dataSourceProperties4Master() {
		return new DataSourceProperties();

	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.slave")
	public DataSourceProperties dataSourceProperties4Slave() {
		return new DataSourceProperties();

	}

	@Bean(name = "slaveDataSource")
	@Qualifier("slaveDataSource")
	public DataSource slaveDataSource() {
		logger.info("数据库连接池创建中.......");
		DataSourceProperties dsProps = dataSourceProperties4Slave();
		return getDataSource(dsProps);
	}

	@Bean(name = "sqlSessionFactory")
	@Primary
	public SqlSessionFactory masterSqlSessionFactory(DynamicDataSource dataSource,
			org.apache.ibatis.session.Configuration config) throws Exception {
		VFS.addImplClass(SpringBootVFS.class);
		final SqlSessionFactoryBean sessionFactory = new PackagesSqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setConfiguration(config);

		String mapperLocations = "classpath*:*com/ump/**/mapper/**/*.xml";
		String typeAliasesPackage = "com.ump.**.entity";
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
		sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
		return sessionFactory.getObject();
	}

	/**
	 * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
	 * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
	 */
	@Bean
	@Primary
	public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
			@Qualifier("slaveDataSource") DataSource slaveDataSource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(ConstantUtil.DSType.DS_TYPE_SYSDB, masterDataSource);
		targetDataSources.put(ConstantUtil.DSType.DS_TYPE_UMPDB, slaveDataSource);

		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
		dataSource.setDefaultTargetDataSource(masterDataSource);// 默认的datasource设置为myTestDbDataSource

		return dataSource;
	}

	/**
	 * 配置事务管理器
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	@ConfigurationProperties(prefix = "mybatis-plus.configuration")
	public org.apache.ibatis.session.Configuration globalConfiguration() {
		return new org.apache.ibatis.session.Configuration();
	}

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("mysql");
		return page;
	}
}
