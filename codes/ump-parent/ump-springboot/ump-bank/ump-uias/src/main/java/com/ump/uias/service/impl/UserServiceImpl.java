package com.ump.uias.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ump.uias.entity.User;
import com.ump.uias.mapper.UserMapper;
import com.ump.uias.service.IUserService;

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User get(String userId) {
		return this.getOne(new QueryWrapper(userId));
	}

	@Override
	public List<User> selectAll() {
		List<User> userList = this.list(null);
		return userList;
	}

}
