package com.ump.sys.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.ump.commons.web.ResultRsp;
import com.ump.sys.user.entity.User;

/**
 * 
 * @author fangyh
 * @date 2018-09-19 21:14:19
 * @version 1.0.0
 */
@Controller
public class LoginCtrler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/login")
	@ResponseBody
	public ResultRsp<User> login(User user) {
		ResultRsp<User> rsp = new ResultRsp<>();
		Preconditions.checkNotNull(user, "user cannot be null.");
		Preconditions.checkNotNull(user.getUserName(), "username cannot be null.");
		Preconditions.checkNotNull(user.getUserPwd(), "userpwd cannot be null.");
		String userName = user.getUserName();
		String userPwd = user.getUserPwd();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd, false);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (IncorrectCredentialsException e) {
			logger.error("sdgeee", e);
			rsp.setFailMsg("密码错误");
		} catch (AuthenticationException e) {
			logger.error("sdgeee", e);
			rsp.setFailMsg("登录失败");
		}
		return rsp;
	}
}
