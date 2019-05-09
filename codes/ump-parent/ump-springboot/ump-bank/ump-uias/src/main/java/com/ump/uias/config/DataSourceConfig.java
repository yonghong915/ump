package com.ump.uias.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.ump.uias.datasource.DatabaseType;
import com.ump.uias.datasource.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
//@MapperScan(basePackages = { "com.ump.uias.modules.system.mapper" })
@MapperScan(value={"com.ump.**.modules.**.mapper*"})
public class DataSourceConfig {
	private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	@Value("${spring.datasource.type}")
	private Class<? extends DataSource> dataSourceType;

	@Bean(name = "defaultDataSource")
	@Qualifier("defaultDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	// @Primary
	public DataSource defaultDataSource() {
		logger.info("数据库连接池创建中.......");
		DataSourceProperties dsProps = dataSourceProperties();
		return getDataSource(dsProps);
	}

	private DataSource getDataSource(DataSourceProperties dataSourceProperties) {
		DataSourceBuilder<?> dsBuilder = DataSourceBuilder.create();
		// TODO
		// 后续对密码加密解密
		String password = dataSourceProperties.getPassword();
		// TODO
		dsBuilder.password(password);
		dsBuilder.type(getDataSourceType());
		return dsBuilder.build();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();

	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSourceProperties primaryDataSourceProperties() {
		return new DataSourceProperties();

	}

	private Class<? extends DataSource> getDataSourceType() {
		return (null == dataSourceType) ? HikariDataSource.class : dataSourceType;
	}

	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataSource() {
		logger.info("数据库连接池创建中.......");
		DataSourceProperties dsProps = primaryDataSourceProperties();
		return getDataSource(dsProps);
	}

	@Bean(name = "sqlSessionFactory")
	@Primary
	public SqlSessionFactory primarySqlSessionFactory(DynamicDataSource dataSource,
			org.apache.ibatis.session.Configuration config) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		VFS.addImplClass(SpringBootVFS.class);
		sessionFactory.setConfiguration(config);
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:config/mapper/**/*.xml"));
		//sessionFactory.setTypeAliasesPackage("com.ump.uias.modules.system.mapper,com.ump.core.modules.system.mapper");
		return sessionFactory.getObject();
	}

	/**
	 * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
	 * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
	 */
	@Bean
	@Primary
	public DynamicDataSource dataSource(@Qualifier("defaultDataSource") DataSource defaultDataSource,
			@Qualifier("primaryDataSource") DataSource primaryDataSource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DatabaseType.UMPDB, defaultDataSource);
		targetDataSources.put(DatabaseType.JEECG, primaryDataSource);

		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
		dataSource.setDefaultTargetDataSource(defaultDataSource);// 默认的datasource设置为myTestDbDataSource

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
}
