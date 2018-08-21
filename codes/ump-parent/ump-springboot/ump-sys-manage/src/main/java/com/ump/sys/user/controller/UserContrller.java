package com.ump.sys.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ump.commons.web.ResultRsp;
import com.ump.sys.user.entity.User;
import com.ump.sys.user.service.IUserService;

@RestController
public class UserContrller {
	Logger logger = LoggerFactory.getLogger(UserContrller.class);

	@Autowired
	private IUserService userService;

	@RequestMapping("/")
	public String hello() {
		return "welcome";
	}

	@RequestMapping(value = "/queryUserById", method = RequestMethod.POST)
	public ResultRsp<User> queryUserById(String userId) {
		ResultRsp<User> rsp = new ResultRsp<>();
		User user = userService.queryById(userId);
		rsp.setSucceed(user);
		return rsp;
	}

	public ResultRsp<PageInfo<User>> queryUsers(User user, Page<User> page) {
		ResultRsp<PageInfo<User>> rsp = new ResultRsp<>();
		PageInfo<User> pageInfo = userService.queryUsers(user, page);
		rsp.setSucceed(pageInfo);
		return rsp;
	}
}
