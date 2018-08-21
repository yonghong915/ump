package com.ump.sys.user.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ump.core.base.service.BaseServiceImpl;
import com.ump.sys.user.entity.User;
import com.ump.sys.user.mapper.UserMapper;
import com.ump.sys.user.service.IUserService;

@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Transactional
	@Override
	public void insert(User user) {
		userMapper.deleteById("");

	}

	@Override
	public PageInfo<User> queryUsers(User user, Page<User> page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<User> usrLst = userMapper.find(user);
		return new PageInfo<>(usrLst);
	}

	public PageInfo<User> queryList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userMapper.find(null);
		PageInfo<User> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
}
