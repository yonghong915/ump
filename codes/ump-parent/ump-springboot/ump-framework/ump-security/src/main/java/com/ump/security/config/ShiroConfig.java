//package com.ump.security.config;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.Filter;
//
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.crazycake.shiro.RedisCacheManager;
//import org.crazycake.shiro.RedisManager;
//import org.crazycake.shiro.RedisSessionDAO;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.ump.security.filter.KickoutSessionControlFilter;
//import com.ump.security.service.impl.MyShiroRealm;
//import com.ump.security.shiro.authc.JwtFilter;
//import com.ump.security.shiro.authc.ResourceCheckFilter;
//
///**
// * 
// * @author fangyh
// * @date 2018-09-19 10:54:19
// * @version 1.0.0
// */
//@Configuration
//public class ShiroConfig {
//	@Resource
//	private RedisSessionDAO sessionDAO;
//
//	/**
//	 * Filter Chain定义说明
//	 * 
//	 * 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过 3、部分过滤器可指定参数，如perms，roles
//	 */
//	@Bean
//	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//		shiroFilterFactoryBean.setSecurityManager(securityManager);
//
////		shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
////		shiroFilterFactoryBean.setSuccessUrl(successUrl);
//		// 没有登陆的用户只能访问登陆页面
//		shiroFilterFactoryBean.setLoginUrl("/auth/login");
//		// 登录成功后要跳转的链接
//		shiroFilterFactoryBean.setSuccessUrl("/auth/index");
//		// 未授权界面; ----这个配置了没卵用，具体原因想深入了解的可以自行百度
//		// shiroFilterFactoryBean.setUnauthorizedUrl("/auth/403");
//
//		// 权限控制map.// 拦截器
//		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//		filterChainDefinitionMap.put("/css/**", "anon");
//		filterChainDefinitionMap.put("/js/**", "anon");
//		filterChainDefinitionMap.put("/img/**", "anon");
//		filterChainDefinitionMap.put("/auth/login", "anon");
//		filterChainDefinitionMap.put("/auth/logout", "logout");
//		filterChainDefinitionMap.put("/auth/kickout", "anon");
//		filterChainDefinitionMap.put("/**", "authc,kickout");
//		// 配置不会被拦截的链接 顺序判断
//		filterChainDefinitionMap.put("/sys/login", "anon"); // 登录接口排除
//		filterChainDefinitionMap.put("/sys/common/view/**", "anon");// 图片预览不限制token
//		filterChainDefinitionMap.put("/api/auth/logout", "anon");// 退出登录
//		filterChainDefinitionMap.put("/auth/2step-code", "anon");// 退出登录
//
//		filterChainDefinitionMap.put("/**.js", "anon");
//		filterChainDefinitionMap.put("/**.css", "anon");
//		filterChainDefinitionMap.put("/druid/**", "anon");
//		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
//		filterChainDefinitionMap.put("/swagger**/**", "anon");
//		filterChainDefinitionMap.put("/webjars/**", "anon");
//		filterChainDefinitionMap.put("/v2/**", "anon");
//
//		filterChainDefinitionMap.put("/processInstance/**", "anon");
//		filterChainDefinitionMap.put("/task/**", "anon");
//		// TODO 排除Online请求
//		filterChainDefinitionMap.put("/auto/cgform/**", "anon");
//
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//
//		// 自定义拦截器
//		Map<String, Filter> filterMap = new LinkedHashMap<>();
//		// 限制同一帐号同时在线的个数。
//		filterMap.put("kickout", kickoutSessionControlFilter());
//		filterMap.put("jwt", new JwtFilter());
//		filterMap.put("resourceCheckFilter", new ResourceCheckFilter());
//		shiroFilterFactoryBean.setFilters(filterMap);
//
//		// 未授权界面;
//		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//		return shiroFilterFactoryBean;
//	}
//
//	@Bean
//	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
//		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
//		aasa.setSecurityManager(securityManager());
//		return new AuthorizationAttributeSourceAdvisor();
//	}
//
//	@Bean
//	public SecurityManager securityManager() {
//		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		securityManager.setRealm(myShiroRealm());
//		// 自定义缓存实现 使用redis
//		// securityManager.setCacheManager(cacheManager());
//		// 自定义session管理 使用redis
//		// securityManager.setSessionManager(sessionManager());
//
//		securityManager.setSessionManager(sessionManager());
//		securityManager.setCacheManager(redisCacheManager());
//		return securityManager;
//	}
//
//	/**
//	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
//	 * 
//	 * @return
//	 */
//	@Bean
//	public Realm myShiroRealm() {
//		return new MyShiroRealm();
//	}
//
//	@Bean
//	public RedisCacheManager redisCacheManager() {
//		return new RedisCacheManager();
//	}
//
//	/**
//	 * cacheManager 缓存 redis实现 使用的是shiro-redis开源插件
//	 *
//	 * @return
//	 */
//	public RedisCacheManager cacheManager() {
//		RedisCacheManager redisCacheManager = new RedisCacheManager();
//		redisCacheManager.setRedisManager(redisManager());
//		return redisCacheManager;
//	}
//
//	/**
//	 * 配置shiro redisManager 使用的是shiro-redis开源插件
//	 *
//	 * @return
//	 */
//	public RedisManager redisManager() {
//		RedisManager redisManager = new RedisManager();
//		redisManager.setHost("localhost");
//		//redisManager.setPort(6379);
//		// redisManager.setExpire(1800);// 配置缓存过期时间
//		redisManager.setTimeout(0);
//		// redisManager.setPassword(password);
//		return redisManager;
//	}
//
//	/**
//	 * Session Manager 使用的是shiro-redis开源插件
//	 */
//	@Bean
//	public DefaultWebSessionManager sessionManager() {
//		// DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//		// sessionManager.setSessionDAO(redisSessionDAO());
//		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//		sessionManager.setSessionDAO(sessionDAO);
//		sessionManager.setGlobalSessionTimeout(1800);
//		sessionManager.setCacheManager(redisCacheManager());
//		return sessionManager;
//	}
//
//	/**
//	 * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
//	 */
//	@Bean
//	public RedisSessionDAO redisSessionDAO() {
//		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//		redisSessionDAO.setRedisManager(redisManager());
//		return redisSessionDAO;
//	}
//
//	/**
//	 * 限制同一账号登录同时登录人数控制
//	 *
//	 * @return
//	 */
//	@Bean
//	public KickoutSessionControlFilter kickoutSessionControlFilter() {
//		KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
//		kickoutSessionControlFilter.setCacheManager(cacheManager());
//		kickoutSessionControlFilter.setSessionManager(sessionManager());
//		kickoutSessionControlFilter.setKickoutAfter(false);
//		kickoutSessionControlFilter.setMaxSession(1);
//		kickoutSessionControlFilter.setKickoutUrl("/auth/kickout");
//		return kickoutSessionControlFilter;
//	}
//
//	/***
//	 * 授权所用配置
//	 *
//	 * @return
//	 */
//	@Bean
//	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//		return defaultAdvisorAutoProxyCreator;
//	}
//
//	/***
//	 * 使授权注解起作用不如不想配置可以在pom文件中加入 <dependency>
//	 * <groupId>org.springframework.boot</groupId>
//	 * <artifactId>spring-boot-starter-aop</artifactId> </dependency>
//	 * 
//	 * @param securityManager
//	 * @return
//	 */
//	@Bean
//	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//		return authorizationAttributeSourceAdvisor;
//	}
//
//	/**
//	 * Shiro生命周期处理器
//	 *
//	 */
//	@Bean
//	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//		return new LifecycleBeanPostProcessor();
//	}
//}
