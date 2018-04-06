package com.ump.cfn.sysmgr.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.ump.exception.BusinessException;
import org.ump.exception.ServiceException;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.cfn.sysmgr.user.service.UserService;
import com.ump.core.base.controller.BaseCtrler;
import com.ump.core.util.web.AjaxRsp;
import com.ump.core.util.web.StatusCode;

@Controller
@RequestMapping("/login")
public class LoginCtrler extends BaseCtrler<User> {

	@Autowired
	private UserService userService;

	/**
	 * login index page
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loginIndex")
	public ModelAndView toLogin() throws Exception {
		logger.info("loginIndex->access login page");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/login/login");
		return mv;
	}

	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(String userName, String userPwd, User user, HttpServletRequest request) throws Exception {
		logger.info("login->params={}", userName);
		String verifyCode = "123456";
		StatusCode sc = null;
		try {
			sc = userService.login(userName, userPwd, verifyCode);
		} catch (BusinessException e) {
			logger.error("", e.getMsg());
			sc = StatusCode.FAIL;
		}
		AjaxRsp ar = new AjaxRsp(sc);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", ar);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.hasRole("/dge");
		return new ModelAndView("index", map);
	}

	/**
	 * 用户登出
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		// request.getSession().invalidate();
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
	}
}
