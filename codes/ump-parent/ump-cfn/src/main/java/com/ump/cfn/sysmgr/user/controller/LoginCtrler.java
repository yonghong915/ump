package com.ump.cfn.sysmgr.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.core.base.controller.BaseCtrler;
import com.ump.core.util.web.AjaxRsp;
import com.ump.core.util.web.StatusCode;

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
	@ResponseBody
	public AjaxRsp login(String params) throws Exception {
		logger.info("login->params={}", params);
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
		AjaxRsp ar = new AjaxRsp();
		ar.setRspObj("true");
		return ar;
	}

	@RequestMapping("/toLoginSuccess")
	public String toLoginSuccess() throws Exception {
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
		return "index";
	}
}
