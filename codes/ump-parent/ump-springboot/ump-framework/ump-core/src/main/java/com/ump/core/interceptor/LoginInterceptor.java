package com.ump.core.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.collect.Sets;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("");
		String basePath = request.getContextPath();
		String path = request.getRequestURI();
		if (!doLoginInterceptor(path, basePath)) {
			return true;
		}
		HttpSession session = request.getSession();
		session.getAttribute("userToken");
		return super.preHandle(request, response, handler);
	}

	private boolean doLoginInterceptor(String path, String basePath) {
		Set<String> notLoginPaths = Sets.newHashSet();
		notLoginPaths.contains(path);
		return false;
	}

}
