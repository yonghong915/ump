package com.ump.sys.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ump.sys.user.service.impl.MyShiroRealm;

/**
 * 
 * @author fangyh
 * @date 2018-09-19 10:54:19
 * @version 1.0.0
 */
@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		
//		shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
//		shiroFilterFactoryBean.setSuccessUrl(successUrl);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

	@Bean
	public Realm myShiroRealm() {
		return new MyShiroRealm();
	}

}
