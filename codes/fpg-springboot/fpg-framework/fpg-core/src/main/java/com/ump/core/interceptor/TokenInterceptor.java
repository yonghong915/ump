package com.ump.core.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author fangyh
 * @date 2018-09-19 14:25:19
 * @version 1.0.0
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation = method.getAnnotation(Token.class);
			if (null != annotation) {
				boolean needSaveSession = annotation.save();
				if (needSaveSession) {
					request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
				}
				boolean needRemoveSession = annotation.remove();
				if (needRemoveSession) {
					if (isRepeatSubmit(request)) {
						return false;
					}
					request.getSession(false).removeAttribute("token");
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute("token");
		if (null == serverToken) {
			return true;
		}
		String clientToken = request.getParameter("token");
		if (null == clientToken) {
			return true;
		}
		if (!serverToken.equals(clientToken)) {
			return true;
		}
		return false;
	}
}
