/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-21 11:30:34
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		System.out.println("JwtAuthenticationEntryPoint:" + authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "没有凭证");
	}

}
