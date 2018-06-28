package com.ump.cfn.sysmgr.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.cfn.sysmgr.user.service.UserService;
import com.ump.core.base.controller.BaseCtrler;
import com.ump.core.util.web.AjaxRsp;

@Controller
@RequestMapping("/backstage/user")
public class UserCtrler extends BaseCtrler<User> {
	@Autowired
	private UserService userService;

	/**
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public String index() throws Exception {
		logger.info("index->access login page");
		return "index";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@RequiresPermissions("/findByPage")
	@ResponseBody
	public String findByPage(Model model) {
		logger.info("index......");
		Subject subject = SecurityUtils.getSubject();
		subject.isAuthenticated();
		// Object obj = subject.getPrincipal();
		// boolean auth = subject.isAuthenticated();
		return "index";
	}

	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRsp saveUser(User user) {
		logger.info("index......");
		userService.save(user);
		AjaxRsp ar = new AjaxRsp();
		return ar;
	}
}
