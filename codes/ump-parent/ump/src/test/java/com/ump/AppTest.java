package com.ump;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ump.cust.model.Cust;
import com.ump.cust.service.CustService;

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
		CustService custDao = (CustService) appCtx.getBean("custService");
//		Cust cust = new Cust();
//		cust.setId("33336563");
//		cust.setCustCode("34455");
//		cust.setCustName("andsmdg");
//		custDao.insertCust(cust);
		System.out.println(custDao.findCustByCustCode("34455"));
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
