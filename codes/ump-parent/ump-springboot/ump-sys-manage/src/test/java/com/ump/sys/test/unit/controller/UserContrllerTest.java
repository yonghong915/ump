package com.ump.sys.test.unit.controller;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.ump.commons.web.ResultRsp;
import com.ump.sys.user.controller.UserContrller;
import com.ump.sys.user.model.User;
import com.ump.sys.user.service.IUserService;

/**
 * UserContrller unit test
 * 
 * @author fangyh
 * @date 2018-08-06 21:40:06
 * @version 1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserContrllerTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Before
	public void setUp() throws Exception {
		// 初始化测试用例类中由Mockito的注解标注的所有模拟对象
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	private IUserService userService;

	@InjectMocks
	UserContrller userContrller;

	/**
	 * Unit test queryUserById
	 */
	@Test
	public void testQueryUserById() {
		logger.info("Unit test queryUserById...");
		String userId = "1000000";
		Mockito.when(userService.queryById(userId)).thenReturn(new User());
		ResultRsp<?> r = userContrller.queryUserById(userId);
		Mockito.verify(userService).queryById(userId);
		Assert.assertEquals("000000", r.getRspCode());
	}

	/**
	 * Unit test queryUsers
	 */
	@Test
	public void testQueryUsers() {
		logger.info("Unit test queryUsers...");
		User user = new User();
		user.setUserId("100000");
		user.setUserName("fangyh");
		Mockito.when(userService.queryUsers(user)).thenReturn(new ArrayList<User>());
		ResultRsp<?> r = userContrller.queryUsers(user);
		Mockito.verify(userService).queryUsers(user);
		Assert.assertEquals("000000", r.getRspCode());
	}
}
