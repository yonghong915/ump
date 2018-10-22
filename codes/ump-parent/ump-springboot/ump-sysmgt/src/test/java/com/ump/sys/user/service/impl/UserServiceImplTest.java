package com.ump.sys.user.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.weakref.jmx.internal.guava.collect.Lists;

import com.ump.commons.email.EmailUtil;
import com.ump.sys.user.entity.User;
import com.ump.sys.user.mapper.UserMapper;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ EmailUtil.class, UserServiceImpl.class })
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserMapper userMapper;

	// @BeforeClass
	// public static void suppressUnWanted() {
	// //suppress(constructor(UserMapper.class));
	// }
	@Test
	public void testQueryList() {
		User user = new User();
		List<User> userList = Lists.newArrayList();
		PowerMockito.when(userMapper.find(user)).thenReturn(userList);
	}

	@Test
	public void testQueryUserByUserName() {
		String userName = "aaaaa";
		userService.queryUserByUserName(userName);
		// User user = userService.queryById("");
	}
}
