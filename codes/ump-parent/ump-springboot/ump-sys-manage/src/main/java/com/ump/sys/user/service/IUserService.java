package com.ump.sys.user.service;

import java.util.List;

import com.ump.core.base.service.BaseService;
import com.ump.sys.user.entity.User;

public interface IUserService extends BaseService<User> {

	void insert(User user);

	List<User> queryUsers(User user);
}
