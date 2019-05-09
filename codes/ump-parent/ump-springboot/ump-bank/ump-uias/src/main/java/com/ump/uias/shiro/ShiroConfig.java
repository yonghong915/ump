package com.ump.uias.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class ShiroConfig {

	/**
	 * Filter Chain定义说明
	 * 
	 * 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过 3、部分过滤器可指定参数，如perms，roles
	 */
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/sys/login", "anon"); // 登录接口排除
		filterChainDefinitionMap.put("/auth/2step-code", "anon");// 登录验证码
		filterChainDefinitionMap.put("/test/jeecgDemo/**", "anon"); // 测试接口
		filterChainDefinitionMap.put("/test/jeecgOrderMain/**", "anon"); // 测试接口
		filterChainDefinitionMap.put("/**/exportXls", "anon"); // 导出接口
		filterChainDefinitionMap.put("/**/importExcel", "anon"); // 导入接口
		filterChainDefinitionMap.put("/sys/common/view/**", "anon");// 图片预览不限制token
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/**/*.js", "anon");
		filterChainDefinitionMap.put("/**/*.css", "anon");
		filterChainDefinitionMap.put("/**/*.html", "anon");
		filterChainDefinitionMap.put("/**/*.svg", "anon");
		filterChainDefinitionMap.put("/**/*.jpg", "anon");
		filterChainDefinitionMap.put("/**/*.png", "anon");
		filterChainDefinitionMap.put("/**/*.ico", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/swagger**/**", "anon");
		filterChainDefinitionMap.put("/webjars/**", "anon");
		filterChainDefinitionMap.put("/v2/**", "anon");

		// 性能监控
		filterChainDefinitionMap.put("/actuator/metrics/**", "anon");
		filterChainDefinitionMap.put("/actuator/httptrace/**", "anon");
		filterChainDefinitionMap.put("/redis/**", "anon");

		// TODO 排除Online请求
		filterChainDefinitionMap.put("/auto/cgform/**", "anon");
		filterChainDefinitionMap.put("/online/cgreport/api/exportXls/**", "anon");

		// 添加自己的过滤器并且取名为jwt
		Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
		filterMap.put("jwt", new JWTFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		// <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
		filterChainDefinitionMap.put("/**", "jwt");

		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean("securityManager")
	public DefaultWebSecurityManager securityManager(MyRealm myRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);

		/*
		 * 关闭shiro自带的session，详情见文档
		 * http://shiro.apache.org/session-management.html#SessionManagement-
		 * StatelessApplications%28Sessionless%29
		 */
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		securityManager.setSubjectDAO(subjectDAO);

		return securityManager;
	}

	/**
	 * 下面的代码是添加注解支持
	 * 
	 * @return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	/**
	 * // * 注册shiro的Filter，拦截请求 //
	 */
//	@Bean
//	public FilterRegistrationBean<Filter> filterRegistrationBean(SecurityManager securityManager,
//			IUserService userService) throws Exception {
//		FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<Filter>();
//		filterRegistration.setFilter((Filter) shiroFilter(securityManager, userService).getObject());
//		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//		filterRegistration.setAsyncSupported(true);
//		filterRegistration.setEnabled(true);
//		filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
//
//		return filterRegistration;
//	}

//	@Bean("shiroFilter")
//	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, ISysUserService userService) {
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//		shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//		// 添加Shiro内置过滤器
//		/**
//		 * Shiro内置过滤器，可以实现权限相关的拦截器 常用的过滤器： anon：无需认证（登录）可以访问 authc:必须认证才可以访问
//		 * user:如果使用rememberMe的功能可以直接访问 perms:该资源必须得到资源权限才可以访问 role：该资源必须得到角色权限才可以访问
//		 *
//		 *
//		 *
//		 */
//		// 拦截器
//		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//		// 配置不会被拦截的链接 顺序判断
//		filterChainDefinitionMap.put("/sys/login", "anon");
//		filterChainDefinitionMap.put("/user/login", "anon");
//		filterChainDefinitionMap.put("/index", "anon");
//		filterChainDefinitionMap.put("/**.js", "anon");
//		filterChainDefinitionMap.put("/druid/**", "anon");
//		filterChainDefinitionMap.put("/swagger**/**", "anon");
//		filterChainDefinitionMap.put("/webjars/**", "anon");
//		filterChainDefinitionMap.put("/v2/**", "anon");
//
//		// 登出
//		filterChainDefinitionMap.put("/logout", "logout");
//		// 对所有用户认证
//		filterChainDefinitionMap.put("/**", "authc");
//
//		// 添加自己的过滤器并且取名为jwt
//		Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
//		filterMap.put("jwt", new JWTFilter());
//		// filterMap.put("authcToken", createAuthFilter(userService));
//		// filterMap.put("anyRole", createRolesFilter());
//		shiroFilterFactoryBean.setFilters(filterMap);
//
//		// 访问401和404页面不通过我们的Filter
//		filterChainDefinitionMap.put("/401", "anon");
//		filterChainDefinitionMap.put("/noAuth", "anon");
//		// 访问 /unauthorized/** 不通过JWTFilter
//		filterChainDefinitionMap.put("/unauthorized/**", "anon");
//		// 登录
//
//		// <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
//		filterChainDefinitionMap.put("/**", "jwt");
//
//		shiroFilterFactoryBean.setLoginUrl("/sys/toLogin");
//		// 首页
//		shiroFilterFactoryBean.setSuccessUrl("/index");
//		// 未授权界面;
//		shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//
//		return shiroFilterFactoryBean;
//	}
//
//	// 将自己的验证方式加入容器
//	@Bean
//	public MyRealm myShiroRealm() {
//		return new MyRealm();
//	}
//
//	// 权限管理，配置主要是Realm的管理认证
//	@Bean("securityManager")
//	public SecurityManager securityManager() {
//		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		securityManager.setRealm(myShiroRealm());
//
//		// 关闭自带session
//		DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
//		evaluator.setSessionStorageEnabled(false);
//
//		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//		subjectDAO.setSessionStorageEvaluator(evaluator);
//
//		securityManager.setSubjectDAO(subjectDAO);
//		return securityManager;
//	}
//
//	@Bean
//	@DependsOn("lifecycleBeanPostProcessor")
//	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//		return defaultAdvisorAutoProxyCreator;
//	}
//
//	@Bean
//	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//		return new LifecycleBeanPostProcessor();
//	}
//
//	// 加入注解的使用，不加入这个注解不生效
//	@Bean
//	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//		return authorizationAttributeSourceAdvisor;
//	}

//	@Bean
//	protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
//		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//		chainDefinition.addPathDefinition("/login", "noSessionCreation,anon"); // login不做认证，noSessionCreation的作用是用户在操作session时会抛异常
//		chainDefinition.addPathDefinition("/logout", "noSessionCreation,authcToken[permissive]"); // 做用户认证，permissive参数的作用是当token无效时也允许请求访问，不会返回鉴权未通过的错误
//		chainDefinition.addPathDefinition("/image/**", "anon");
//		chainDefinition.addPathDefinition("/admin/**", "noSessionCreation,authcToken,anyRole[admin,manager]"); // 只允许admin或manager角色的用户访问
//		chainDefinition.addPathDefinition("/article/list", "noSessionCreation,authcToken");
//		chainDefinition.addPathDefinition("/article/*", "noSessionCreation,authcToken[permissive]");
//		chainDefinition.addPathDefinition("/**", "noSessionCreation,authcToken"); // 默认进行用户鉴权
//		return chainDefinition;
//	}

	// 注意不要加@Bean注解，不然spring会自动注册成filter
//	protected JwtFilter createAuthFilter(IUserService userService) {
//		return new JwtFilter(userService);
//	}

//	// 注意不要加@Bean注解，不然spring会自动注册成filter
//	protected AnyRolesAuthorizationFilter createRolesFilter() {
//		return new AnyRolesAuthorizationFilter();
//	}

//	/**
//	 * 初始化Authenticator
//	 */
//	@Bean
//	public Authenticator authenticator(IUserService userService) {
//		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
//		// 设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
//		authenticator.setRealms(Arrays.asList(jwtShiroRealm(userService), dbShiroRealm(userService)));
//		// 设置多个realm认证策略，一个成功即跳过其它的
//		authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
//		return authenticator;
//	}

//	/**
//	 * 禁用session, 不保存用户登录状态。保证每次请求都重新认证。
//	 * 需要注意的是，如果用户代码里调用Subject.getSession()还是可以用session，如果要完全禁用，要配合下面的noSessionCreation的Filter来实现
//	 */
//	@Bean
//	protected SessionStorageEvaluator sessionStorageEvaluator() {
//		DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
//		sessionStorageEvaluator.setSessionStorageEnabled(false);
//		return sessionStorageEvaluator;
//	}

//	/**
//	 * 用于用户名密码登录时认证的realm
//	 */
//	@Bean("dbRealm")
//	public Realm dbShiroRealm(UserService userService) {
//		DbShiroRealm myShiroRealm = new DbShiroRealm(userService);
//		return myShiroRealm;
//	}

//	/**
//	 * 用于JWT token认证的realm
//	 */
//	@Bean("jwtRealm")
//	public Realm jwtShiroRealm(IUserService userService) {
//		MyRealm myShiroRealm = new MyRealm(userService);
//		return myShiroRealm;
//	}

//	@Bean("securityManager")
//	public SecurityManager securityManager(MyRealm myRealm) {
//		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		securityManager.setRealm(myRealm);
//
//		/*
//		 * 关闭shiro自带的session，详情见文档
//		 * http://shiro.apache.org/session-management.html#SessionManagement-
//		 * StatelessApplications%28Sessionless%29
//		 */
//		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//		securityManager.setSubjectDAO(subjectDAO);
//
//		return securityManager;
//	}
}
