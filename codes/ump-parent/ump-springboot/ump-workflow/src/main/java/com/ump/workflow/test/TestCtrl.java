package com.ump.workflow.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCtrl {
	@Value("${datasource}") // git配置文件里的key
	String myww;

	@RequestMapping(value = "/hi")
	public String hi() {
		return "aaa=" + myww;
	}
}
