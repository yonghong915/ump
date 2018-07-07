package com.ump.sys.user.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ump.core.base.service.BaseServiceImpl;
import com.ump.sys.user.mapper.UserMapper;
import com.ump.sys.user.model.User;
import com.ump.sys.user.service.IUserService;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Transactional
	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub

	}
}
