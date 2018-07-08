package com.ump.sys.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ump.sys.SysManageApplication;
import com.ump.sys.config.DataSourceProperties;
import com.ump.sys.user.model.User;
import com.ump.sys.user.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SysManageApplication.class })
public class TestSysManage {
	Logger logger = LoggerFactory.getLogger(TestSysManage.class);
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private DataSourceProperties props;

	@Autowired
	private IUserService userService;

	@Test
	public void testDbProperties() {
		logger.info(props.getJdbcUrl());
	}

	@Test
	public void testUser() throws JsonProcessingException {
		User user = userService.queryById("aaa");
		String json = mapper.writeValueAsString(user);
		logger.info("UserJson={}", json);
		assertEquals("zhansan", user.getUserName());

		
		
		
	}

}
