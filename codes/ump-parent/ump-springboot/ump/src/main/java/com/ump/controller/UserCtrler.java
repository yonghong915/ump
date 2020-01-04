/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.controller;

import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ump.common.log.LogCodeEnum;
import com.ump.common.log.annotation.AutoLog;
import com.ump.entity.sys.UserEntity;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-01 21:43:55
 *
 */
@Controller
@RequestMapping("/user")
public class UserCtrler {

	@AutoLog(logCode = LogCodeEnum.EXIT, remark = "forward index page")
	@RequestMapping("/index")
	public String index(Model model) {
		UserEntity boy = new UserEntity();
		boy.setUserName("weber");
		boy.setUserCode("1235");
		boy.setUserId(UUID.randomUUID().toString());
		model.addAttribute("user", boy);
		return "index";
	}

	@GetMapping(value = "/test")
	public String test() {
		return "Hello Spring Security";
	}

	@PreAuthorize("hasAnyRole('USER')")
	@PostMapping(value = "/testNeed")
	public String testNeed() {
		return "testNeed";
	}
}
