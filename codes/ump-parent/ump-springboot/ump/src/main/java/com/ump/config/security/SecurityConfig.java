/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-01 22:02:08
 *
 */
@Configuration
@EnableWebSecurity // 这个注解必须加，开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true) // 保证post之前的注解可以使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	JwtAuthorizationTokenFilter authenticationTokenFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
	}

	// 拦截在这配
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().authorizeRequests()
				.antMatchers("/login").permitAll().antMatchers("/haha").permitAll().antMatchers("/h2").permitAll().antMatchers("/sysUser/test")
				.permitAll().antMatchers(HttpMethod.OPTIONS, "/**").anonymous().anyRequest().authenticated() // 剩下所有的验证都需要验证
				.and().csrf().disable() // 禁用 Spring Security 自带的跨域处理
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session

		http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
	@Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }
	
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
