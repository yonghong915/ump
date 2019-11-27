package com.ump.core.modules.system.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import com.ump.commons.constant.ConstantUtil;
import com.ump.commons.web.ResultRsp;
import com.ump.commons.web.ResultUtil;
import com.ump.core.common.aspect.annotation.AutoLog;
import com.ump.core.modules.system.entity.SysUser;
import com.ump.core.modules.system.service.ISysUserService;
import com.ump.core.shiro.JWTUtil;
import com.ump.core.util.MD5Util;

@RestController
@RequestMapping("/sys")
public class SysLoginCtrler {
	@Autowired
	private ISysUserService userService;

	@Autowired
	private Producer producer;

	// 退出的时候是get请求，主要是用于退出
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login() {
//		return "login";
//	}

	@PostMapping("/login")
	@AutoLog(value = "login", logType = ConstantUtil.LogType.LOGIN_TYPE)
	public ResultRsp<?> login(@RequestParam String username, @RequestParam String password) {
		ResultRsp<?> rsp = ResultUtil.success();
		Assert.notNull(username, "用户名不能为空");
		Assert.notNull(password, "密码不能为空");
		SysUser sysUser = userService.queryUserByUserName(username);

		if (sysUser == null) {
			// result.error500("该用户不存在");
			// sysBaseAPI.addLog("登录失败，用户名:"+username+"不存在！", CommonConstant.LOG_TYPE_1,
			// null);
			return rsp;
		} else {
			// 密码验证
			String userpassword = MD5Util.MD5Encode(password, "UTF-8");
			String syspassword = sysUser.getUserPwd();
			if (!syspassword.equals(userpassword)) {
				// result.error500("用户名或密码错误");
				return rsp;
			}
			// 生成token
			String token = JWTUtil.sign(username, syspassword);
			// redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
			// 设置超时时间
			// redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token,
			// JwtUtil.EXPIRE_TIME/1000);

			JSONObject obj = new JSONObject();
			obj.put("token", token);
			obj.put("userInfo", sysUser);
			// result.setResult(obj);
			// result.success("登录成功");
			// sysBaseAPI.addLog("用户名: "+username+",登录成功！", CommonConstant.LOG_TYPE_1,
			// null);
			// rsp.setRspObj(obj);
		}
		return rsp;

		// 添加用户认证信息
//		Subject subject = SecurityUtils.getSubject();
//		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
//		// 进行验证，这里可以捕获异常，然后返回对应信息
//		subject.login(usernamePasswordToken);
//		return "login";
	}

	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-store,no-cache");
		response.setContentType("image/jpeg");
		String text = producer.createText();
		BufferedImage image = producer.createImage(text);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	// 登出
	@RequestMapping(value = "/logout")
	public String logout() {
		return "logout";
	}

	// 错误页面展示
	@RequestMapping(value = "/error", method = RequestMethod.POST)
	public String error() {
		return "error ok!";
	}

	// 数据初始化
	@RequestMapping(value = "/addUser")
	public String addUser(@RequestBody Map<String, Object> map) {
		// User user = loginService.addUser(map);
		return "addUser is ok! \n";
	}

	// 角色初始化
	@RequestMapping(value = "/addRole")
	public String addRole(@RequestBody Map<String, Object> map) {
		// Role role = userService.addRole(map);
		return "addRole is ok! \n";
	}

	// 用于拦截完之后去登录页面
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	// 用于未授权识别后跳转的提示页面

	@RequestMapping("/noAuth")
	public String noAuth() {
		return "noAuth";
	}

	// 注解的使用
	@RequiresRoles("admin")
	@RequiresPermissions("create")
	@RequestMapping(value = "/create")
	public String create() {
		return "Create success!";
	}

	public static void main(String[] args) {
		String userpassword = MD5Util.MD5Encode("123456", "UTF-8");
		System.out.println(userpassword);
	}
}
