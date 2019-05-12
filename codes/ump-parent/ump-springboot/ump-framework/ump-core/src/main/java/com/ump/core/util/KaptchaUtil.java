package com.ump.core.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.code.kaptcha.Producer;

@Component
public class KaptchaUtil {
	@Autowired
	private Producer defaultProducer;

	private static Producer producer;

	@PostConstruct
	public void init() {
		producer = defaultProducer;
	}

	public static String getImgText() {
		return producer.createText();
	}
}
