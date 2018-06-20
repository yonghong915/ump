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
		System.out.println("00000000c9ec538cab7f38ef9c67a95742f56ab07b0a37c5be6b02808dbfb4e0".length());
		System.out.println(hexStringToByte("00000000c9ec538cab7f38ef9c67a95742f56ab07b0a37c5be6b02808dbfb4e0").length);
	    //32*8=256
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

	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789abcdef".indexOf(c);
		return b;
	}
}
