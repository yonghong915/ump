package com.ump.codegenerate;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ump.codegenerate.generate.impl.CodeGenerateOne;

/**
 * Unit test for simple App.
 */
public class AppTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testCodeGenerate() {
		if (logger.isInfoEnabled()) {
			logger.info("aaaaaa");
		}
		new CodeGenerateOne().genCodeFile();
		Assert.assertEquals(1, 1);
	}
}
