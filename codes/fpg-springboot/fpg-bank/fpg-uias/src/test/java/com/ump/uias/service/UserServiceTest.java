package com.ump.uias.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.baidu.fsg.uid.UidGenerator;
import com.ump.uias.UiasApplication;
import com.ump.uias.modules.system.entity.SysResource;
import com.ump.uias.modules.system.entity.SysRole;
import com.ump.uias.modules.system.entity.SysUser;
import com.ump.uias.modules.system.service.ISysUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UiasApplication.class }) // 指定启动类
public class UserServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String username = "fangyh";
	@Autowired
	private ISysUserService userService;

	@Autowired
	private UidGenerator uidGenerator;

	@Test
	public void testSaveUser() {
		SysUser entity = new SysUser();
		long uid = uidGenerator.getUID();
		entity.setPkId(uid);
		entity.setUserName("fyy1");
		entity.setRealName("方明");
		entity.setUserDesc("aaaa");
		userService.save(entity);
		System.out.println("test hello 1");
	}

	@Test
	@Ignore
	public void testOne() {
		userService.getById("");
		//userService.queryUserByUserName("aaaa");
		System.out.println("test hello 1");
	}

	@Test
	@Ignore
	public void queryRoles() {
		List<SysRole> roles = userService.queryRolesByUserName(username);
		roles.forEach(role -> {
			logger.info("role=" + JSON.toJSONString(role));
		});
	}

	@Test
	@Ignore
	public void queryResources() {
		List<SysResource> resources = userService.queryResourcesByUserName(username);
		resources.forEach(res -> {
			logger.info("res=" + JSON.toJSONString(res));
		});
	}
}
