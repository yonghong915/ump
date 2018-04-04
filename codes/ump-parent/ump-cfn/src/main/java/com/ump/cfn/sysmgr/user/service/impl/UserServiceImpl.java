package com.ump.cfn.sysmgr.user.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.cfn.sysmgr.user.service.UserService;
import com.ump.core.util.web.StatusCode;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public User findUserByUserCode(String username) {
		User user = new User();
		user.setUsername(username);
		user.setUserCode(username);
		user.setUserPwd("123456");
		return user;
	}

	@Override
	public StatusCode login(String username, String password, String verifyCode) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		StatusCode sc = StatusCode.SUCCESS;
		UsernamePasswordToken token = new UsernamePasswordToken(username, password.toUpperCase());

		session.getAttribute("");
		if (!currentUser.isAuthenticated()) {
			currentUser.login(token);
		}
		if (!currentUser.isAuthenticated()) {
			token.clear();
		}
		
		
		return sc;
	}

}
