package com.ump.cfn.sysmgr.user.service.impl;

import org.springframework.stereotype.Service;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.cfn.sysmgr.user.service.UserService;

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

}
