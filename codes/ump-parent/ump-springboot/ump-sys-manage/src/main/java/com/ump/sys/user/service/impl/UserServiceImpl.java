package com.ump.sys.user.service.impl;

import java.util.List;

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

	// @Transactional
	@Override
	public void insert(User user) {
		userMapper.deleteById("");

	}

	@Override
	public List<User> queryUsers(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
