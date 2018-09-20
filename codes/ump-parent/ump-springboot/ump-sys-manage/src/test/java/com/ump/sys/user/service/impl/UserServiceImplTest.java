package com.ump.sys.user.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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
	public void testQueryUserByUserName() {
		Mockito.when(userMapper.queryById("aaa")).thenReturn(new User());
		//User user = userService.queryById("");
	}
}
