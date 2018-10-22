package com.ump.sys.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ump.commons.web.ResultRsp;
import com.ump.sys.user.entity.User;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ User.class, SecurityUtils.class })
public class LoginCtrlerTest {

	@InjectMocks
	private LoginCtrler loginCtrler;

	@Test
	public void testLogin() {
		User user = new User();
		user.setUserName("fangyh");
		user.setUserPwd("123456");
		PowerMockito.mockStatic(SecurityUtils.class);
//		PowerMockito.when(SecurityUtils.getSubject()).thenReturn(new WebDelegatingSubject(null, null));
//		ResultRsp<User> rsp = loginCtrler.login(user);
	}
}
