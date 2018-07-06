package com.ump.sys.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserContrller {

	@Value("${userCode}")
	private String userName;

	@RequestMapping("/")
	public String hello() {
		return userName + ",welcome";
	}
}
