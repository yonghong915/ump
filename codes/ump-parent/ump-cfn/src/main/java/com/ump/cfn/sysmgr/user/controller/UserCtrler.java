package com.ump.cfn.sysmgr.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.cfn.sysmgr.user.service.UserService;
import com.ump.core.base.controller.BaseCtrler;

@Controller
@RequestMapping("/backstage/user")
public class UserCtrler extends BaseCtrler<User> {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public String findByPage(Model model) {
		logger.info("index......");
		return "index";
	}
}
