package com.ump;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ump.exception.BusinessException;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.cfn.sysmgr.user.service.UserService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */

public class AppTest extends TestCase {
	static Logger logger = LoggerFactory.getLogger(AppTest.class);
	ApplicationContext appCtx = null;

	// @Autowired
	// private CustDao custDao;

	public void setUp() throws Exception {
		String[] configLocations = new String[] { "config/spring/applicationContext-common.xml",
				"config/spring/applicationContext-datasource.xml", "config/spring/applicationContext-mybatis.xml",
				"config/spring/applicationContext-tx.xml" };
		System.out.println(System.getProperty("user.dir"));
		appCtx = new ClassPathXmlApplicationContext(configLocations);
		UserService srv = (UserService) appCtx.getBean("userService");
		try {
			User usr = srv.findUserByUserCode("123456");
			System.out.println(usr.getPid());
		} catch (BusinessException e) {
			System.out.println("aaaaa="+e.getMsg());
		}
	}

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		// appCtx.getBean("dataSource");
		logger.info("aaaaa");
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}
