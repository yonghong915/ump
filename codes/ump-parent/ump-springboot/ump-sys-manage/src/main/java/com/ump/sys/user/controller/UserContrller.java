package com.ump.sys.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ump.sys.user.model.User;
import com.ump.sys.user.service.IUserService;

@RestController
public class UserContrller {
	Logger logger = LoggerFactory.getLogger(UserContrller.class);
	
	@Autowired
	private IUserService userService;

	@RequestMapping("/")
	public String hello() {
		return  "welcome";
	}

	@RequestMapping("/queryUser")
	public Object queryUser() {
		ObjectMapper mapper = new ObjectMapper();
		User user = userService.queryById("aaa");
		String json = "";
		try {
			json = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {

		}
		logger.info("UserJson={}", json);
		return user;
	}
}
