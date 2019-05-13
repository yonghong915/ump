package com.ump.api.uias.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ump.commons.web.ResultRsp;
import com.ump.commons.web.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api("用户登录")
public class LoginCtrler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/login")
	@ApiOperation("登录")
	public ResultRsp<?> login(String username, String password) {
		logger.info("login......");
		ResultRsp<?> ar = ResultUtil.success();
		// 3DES 解密

		// RSA加密->解密->携带TOKEN及3DES密钥
		// ar.setSucceed(cust);

		return ar;
	}
}
