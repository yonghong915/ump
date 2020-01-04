/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ump.config.security.JwtTokenUtil;
import com.ump.entity.sys.UserEntity;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-21 11:31:49
 *
 */
@RestController
public class LoginCtrler {
	@Autowired
	@Qualifier("jwtUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/login")
	public String login(@RequestBody UserEntity sysUser, HttpServletRequest request) {
		final UserDetails userDetails = userDetailsService.loadUserByUsername(sysUser.getUserName());
		final String token = jwtTokenUtil.generateToken(userDetails);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return token;
	}

	@PostMapping("haha")
	public String haha() {
		UserDetails userDetails = (UserDetails)SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return "haha:" + userDetails.getUsername() + "," + userDetails.getPassword();
	}
}
