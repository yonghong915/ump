package com.ump.sys.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ump.sys.user.mapper.UserMapper;
import com.ump.sys.user.service.IUserService;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {
	@Autowired
    private UserMapper userMapper;
}
