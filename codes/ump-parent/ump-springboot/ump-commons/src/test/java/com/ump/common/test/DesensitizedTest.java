package com.ump.common.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.ump.commons.Code;

import desensitization.DesensitizedUtils;

public class DesensitizedTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testDesensitedData() {
		UserInfo baseUserInfo = new UserInfo();
		baseUserInfo.setAccount("12345677898");
		baseUserInfo.setIdCardNo("1234567890");
		baseUserInfo.setMobileNo("13234567890");
		baseUserInfo.setPassword("123456");
		baseUserInfo.setRealName("王小二");
		logger.info("脱敏前：{}", JSON.toJSONString(baseUserInfo));
		logger.info("脱敏后：{}", DesensitizedUtils.getJson(baseUserInfo));
		int paddingLength = 5;
		String hex = "aaa1223";
		String ss = String.format("%0" + paddingLength + "d", 0) + hex;
		String ss1 = String.format("%0" + paddingLength + "d", 2) + hex;
		System.out.println("sssaaa="+StringUtils.leftPad("0", paddingLength,"0")+ hex);
		logger.info("sss={},ssss={}",ss,ss1);
		
		String str = "abcdef脱敏后";
		String ass = Code.encode(str);
		System.out.println("asss="+ass);
		System.out.println(Code.decode(ass));
	}
}
