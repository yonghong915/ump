package com.ump.uias.config;

import java.awt.Color;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * generating verification code
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class KaptchaConfig {
	@Bean
	public DefaultKaptcha producer() {
		Properties props = new Properties();
		props.put(Constants.KAPTCHA_BORDER, "no");
		props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, Color.BLACK);
		props.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "5");
		Config config = new Config(props);
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}
}
