package com.ump.cfn.sysmgr.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.core.base.controller.BaseCtrler;
import com.ump.core.util.web.AjaxRsp;

@Controller
@RequestMapping("/login")
public class LoginCtrler extends BaseCtrler<User> {

	@RequestMapping("/loginIndex")
	public ModelAndView toLogin() throws Exception {
		logger.info("loginIndex->access login page");
		ModelAndView mv = new ModelAndView();
		// HttpSession session = getSession();
		//
		// String secureKey = EncryptAES.getSecretkey();
		// String secureIv = EncryptAES.getSecretIV();
		// logger.info("encryped:[secureKey={},secureIv={}]", secureKey, secureIv);
		// session.setAttribute("secureKey", secureKey);
		// session.setAttribute("secureIv", secureIv);
		// mv.addObject("secureKey", secureKey);
		// mv.addObject("secureIv", secureIv);
		mv.setViewName("system/login/login");
		return mv;
	}

	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, User user, HttpServletRequest request) throws Exception {
		logger.info("login->params={}", username);
		String paramss = request.getParameter("username");
		Subject subject = SecurityUtils.getSubject();
		Object obj = subject.getPrincipal();
		UsernamePasswordToken token = new UsernamePasswordToken(username, "123456");
		subject.login(token);
		request.getSession().setAttribute("user", user);
		// HttpSession sessions = getSession();
		//
		// String secureKey = (String) sessions.getAttribute("secureKey");
		// String secretIv = (String) sessions.getAttribute("secureIv");
		// String jsonStr = EncryptAES.decrypt(secureKey, secretIv, params);
		//
		// String username = JsonUtil.json2Object(String.class, "username", jsonStr);
		// String password = JsonUtil.json2Object(String.class, "password", jsonStr);
		// String verifyCode = JsonUtil.json2Object(String.class, "verifyCode",
		// jsonStr);
		// logger.info("jsonStr={}", jsonStr);
		// logger.info("username={},password={},verifyCode={}", username, password,
		// verifyCode);
		// StatusCode sc = userService.login(username, password, verifyCode);

		// sessions.removeAttribute("secureKey");
		// sessions.removeAttribute("secureIv");
		//// 权限校验。判断是否包含权限。
		String url = "/login/login";
		boolean isPermitted = subject.isPermitted(url);
		AjaxRsp ar = new AjaxRsp();
		ar.setRspObj("true");
		return "index";
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
