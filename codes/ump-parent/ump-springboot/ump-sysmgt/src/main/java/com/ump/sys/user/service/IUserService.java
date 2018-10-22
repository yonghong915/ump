package com.ump.sys.user.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ump.core.base.service.BaseService;
import com.ump.sys.user.entity.User;

public interface IUserService extends BaseService<User> {

	void insert(User user);

	PageInfo<User> queryUsers(User user, Page<User> page);

	User queryUserByUserName(String userName);
}
