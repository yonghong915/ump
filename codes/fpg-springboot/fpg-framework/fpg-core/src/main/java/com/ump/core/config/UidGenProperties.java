package com.ump.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@Configuration
@PropertySource("classpath:config/base/core-common.properties")
@ConfigurationProperties(prefix = "uid")
public class UidGenProperties {
	/***/
	private int timeBits;

	/***/
	private int workerBits;

	/***/
	private int seqBits;

	/***/
	private String epochStr;

	/***/
	private int boostPower;

	/***/
	private long scheduleInterval;
}
