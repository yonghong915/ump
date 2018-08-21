package com.ump.sys.test.unit.controller;

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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ump.commons.web.ResultRsp;
import com.ump.sys.user.controller.UserContrller;
import com.ump.sys.user.entity.User;
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
		Page<User> page = new Page<>();
		page.setPageNum(10);
		page.setPageSize(1);
		Mockito.when(userService.queryUsers(user, page)).thenReturn(new PageInfo<User>());
		ResultRsp<?> r = userContrller.queryUsers(user, page);
		Mockito.verify(userService).queryUsers(user, page);
		Assert.assertEquals("000000", r.getRspCode());
	}
}
