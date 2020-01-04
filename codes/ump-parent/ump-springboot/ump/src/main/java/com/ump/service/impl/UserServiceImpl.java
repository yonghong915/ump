/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ump.entity.sys.UserEntity;
import com.ump.service.IUserService;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-11-24 10:07:32
 *
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Cacheable(value = "users")
	public UserEntity selectUserById(int id) {
		UserEntity user = null;// ;this.usermapper.selectUserById(id);
		System.out.println("1111111111111111111111111");
		return user;
	}

	@Transactional
	public void save() {
	}
}
