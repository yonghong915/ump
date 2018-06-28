package com.ump.test;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ump.cfn.sysmgr.code.model.CodeInfo;
import com.ump.cfn.sysmgr.code.service.CodeInfoService;

public class CodeInfoTest {
	static ApplicationContext appCtx = null;
	static Logger logger = LoggerFactory.getLogger(CodeInfoTest.class);

	@BeforeClass
	public static void setUp() throws Exception {
		String[] configLocations = new String[] { "config/spring/applicationContext-common.xml",
				"config/spring/applicationContext-datasource.xml", "config/spring/applicationContext-mybatis.xml",
				"config/spring/applicationContext-tx.xml" };
		System.out.println(System.getProperty("user.dir"));
		appCtx = new ClassPathXmlApplicationContext(configLocations);
	}

	@Before
	public void before() {
	}

	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void after() {
	}

	@Test
	public void testCodeInfo() {
		CodeInfoService srv = (CodeInfoService) appCtx.getBean("codeInfoService");
		CodeInfo res = srv.findById("1");
		logger.info("result={}", res);
		assertNotNull(res);
	}

	@Ignore("Multiply() Not yet implemented")
	@Test
	public void test() {
		// fail("Not yet implemented");
	}

	@Ignore("Multiply() Not yet implemented")
	@Test
	public void testMultiply() {
	}
}
