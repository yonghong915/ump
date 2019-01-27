package com.ump.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author fangyh
 *
 */
public class XssFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// Do nothing because of
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("XssFilter.....");
		}
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		filterChain.doFilter(new XssHttpServletRequestWrapper(request), servletResponse);
	}

	@Override
	public void destroy() {
		// Do nothing because of
	}

	public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
		public XssHttpServletRequestWrapper(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getHeader(String name) {
			return StringEscapeUtils.escapeHtml4(super.getHeader(name));
		}

		@Override
		public String getQueryString() {
			return StringEscapeUtils.escapeHtml4(super.getQueryString());
		}

		@Override
		public String getParameter(String name) {
			return StringEscapeUtils.escapeHtml4(super.getParameter(name));
		}

		@Override
		public String[] getParameterValues(String name) {
			String[] values = super.getParameterValues(name);
			if (values != null) {
				int length = values.length;
				String[] escapseValues = new String[length];
				for (int i = 0; i < length; i++) {
					escapseValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
				}
				return escapseValues;
			}
			return super.getParameterValues(name);
		}

	}

}
