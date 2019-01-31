package com.ump.uias.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ump.commons.web.ResultRsp;
import com.ump.uias.entity.User;
import com.ump.uias.service.IUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserCtrler {
	@Autowired
	private IUserService userService;

	@ApiOperation(value = "查询用户")
	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
	public ResultRsp<User> get(String userId) {
		ResultRsp<User> result = new ResultRsp<>();
		User user = userService.get(userId);
		result.setRspObj(user);
		return result;
	}

	/**
	 * 假如这个客户端要提供一个getUser的方法
	 * 
	 * @return
	 */
	@GetMapping(value = "/getUser")
	@ResponseBody
	public Map<String, Object> getUser(@RequestParam Integer id) {
		Map<String, Object> data = new HashMap<>();
		data.put("id", id);
		data.put("userName", "admin");
		data.put("from", "provider-B");// 改这里是为了让大家更能理解它负载均衡的机制
		return data;
	}
}
