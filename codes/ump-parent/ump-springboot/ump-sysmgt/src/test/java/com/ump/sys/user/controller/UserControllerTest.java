package com.ump.sys.user.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ump.commons.web.ResultRsp;
import com.ump.sys.user.entity.User;
import com.ump.sys.user.service.impl.UserServiceImpl;

/**
 * UserContrller unit test
 * 
 * @author fangyh
 * @date 2018-08-06 21:40:06
 * @version 1.0.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ User.class })
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserServiceImpl userService;

	@Test
	public void testQueryUserById() {
		User user = PowerMockito.mock(User.class);
		PowerMockito.when(user.getUserName()).thenReturn("123456");
		PowerMockito.when(userService.queryById(user.getUserName())).thenReturn(new User());
		ResultRsp<User> use = userController.queryUserById(user.getUserName());
		// PowerMockito.spy(DemoStatic.class);
		// PowerMockito.when(DemoStatic.sayHello()).thenReturn("my hello");
		Assert.assertNotNull(use);
		// System.out.println(DemoStatic.sayHello()); // my hello
	}

	@Test
	public void testQueryUsers() {
		User user = new User();
		Page<User> page = new Page<>();
		PowerMockito.when(userService.queryUsers(user, page)).thenReturn(new PageInfo<User>());
		ResultRsp<PageInfo<User>> rsp = userController.queryUsers(user, page);
		Assert.assertEquals("000000", rsp.getRspCode());
	}

	// private Logger logger = LoggerFactory.getLogger(getClass());
	//
	// @Before
	// public void setUp() throws Exception {
	// // 初始化测试用例类中由Mockito的注解标注的所有模拟对象
	// MockitoAnnotations.initMocks(this);
	// }
	//
	// @Mock
	// private IUserService userService;
	//
	// @InjectMocks
	// UserController userContrller;
	//
	// /**
	// * Unit test queryUserById
	// */
	// @Test
	// public void testQueryUserById() {
	// logger.info("Unit test queryUserById...");
	// String userId = "1000000";
	// Mockito.when(userService.queryById(userId)).thenReturn(new User());
	// ResultRsp<?> r = userContrller.queryUserById(userId);
	// Mockito.verify(userService).queryById(userId);
	// Assert.assertEquals("000000", r.getRspCode());
	// }
	//
	// /**
	// * Unit test queryUsers
	// */
	// @Test
	// public void testQueryUsers() {
	// logger.info("Unit test queryUsers...");
	// User user = new User();
	// user.setUserId(100000);
	// user.setUserName("fangyh");
	// Page<User> page = new Page<>();
	// page.setPageNum(10);
	// page.setPageSize(1);
	// Mockito.when(userService.queryUsers(user, page)).thenReturn(new
	// PageInfo<User>());
	// ResultRsp<?> r = userContrller.queryUsers(user, page);
	// Mockito.verify(userService).queryUsers(user, page);
	// Assert.assertEquals("000000", r.getRspCode());
	// }
}
