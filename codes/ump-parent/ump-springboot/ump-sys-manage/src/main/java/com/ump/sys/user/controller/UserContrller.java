package com.ump.sys.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	public ResultRsp<List<User>> queryUsers(User user) {
		ResultRsp<List<User>> rsp = new ResultRsp<>();
		List<User> userLst = userService.queryUsers(user);
		rsp.setSucceed(userLst);
		return rsp;
	}
}
