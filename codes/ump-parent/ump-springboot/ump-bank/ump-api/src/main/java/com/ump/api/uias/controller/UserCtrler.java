package com.ump.api.uias.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ump.api.uias.service.IUserService;
import com.ump.commons.web.ResultRsp;
import com.ump.commons.web.ResultUtil;

@RestController
@RequestMapping("/api/user")
public class UserCtrler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/getUser", produces = "application/json;charset=utf-8", method = { RequestMethod.POST })

	public ResultRsp<?> getUser() {
		ResultRsp rsp = ResultUtil.getResultRsp();

		return rsp;
	}
}
