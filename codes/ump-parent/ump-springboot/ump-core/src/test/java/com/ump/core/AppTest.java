package com.ump.core;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
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
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testJDkProxy() {
		//CglibProxy proxy = new CglibProxy();
		// base为生成的增强过的目标类
		// Base base = (Base) proxy.getInstance("com.ump.core.proxy.cglib.Base");
		// base.add();
	}

	public void testCglibProxy() {
		//ServiceImpl aService = new ServiceImpl();
		//Service aServiceProxy = (Service) ProxyUtil.getProxyObj(aService);
		//aServiceProxy.add("aaaa");
	}
}
