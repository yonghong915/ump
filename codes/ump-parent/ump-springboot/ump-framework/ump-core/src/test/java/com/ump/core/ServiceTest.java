package com.ump.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ump.core.util.UidUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringCoreApplication.class }) // 指定启动类
public class ServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void getUid() {
		long uid = UidUtil.getUid();
		logger.info("uid={}", uid);
		assertNotNull(uid);
	}
}
