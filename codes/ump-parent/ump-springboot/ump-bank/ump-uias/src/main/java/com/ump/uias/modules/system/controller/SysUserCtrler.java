package com.ump.uias.modules.system.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.fsg.uid.UidGenerator;
import com.ump.commons.web.ResultRsp;
import com.ump.commons.web.ResultUtil;
import com.ump.uias.modules.system.entity.SysUser;
import com.ump.uias.modules.system.service.ISysUserService;

@RestController
@RequestMapping("/user")
public class SysUserCtrler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ISysUserService userService;

	@Autowired
	private UidGenerator uidGenerator;

	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
	public ResultRsp<SysUser> get(String userId) {
		ResultRsp<?> result = ResultUtil.success();
		SysUser user = userService.get(userId);
		logger.info("user={}", user);
		// result.setRspObj(user);
		return (ResultRsp<SysUser>) result;
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

	@GetMapping("/uid")
	public String uid() {
		long time1 = System.nanoTime();
		long uid = uidGenerator.getUID();
		long time2 = System.nanoTime();
		System.out.println(time2 - time1);
		System.out.println(uidGenerator.parseUID(uid));
		System.out.println("-----------------------------");
		long timeMillis = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			long ssid = uidGenerator.getUID();
			System.out.println("ssid=" + ssid);
		}
		long timeMillis2 = System.currentTimeMillis();
		System.out.println("耗时:" + (timeMillis2 - timeMillis));
		return String.valueOf(uid);
	}
}
