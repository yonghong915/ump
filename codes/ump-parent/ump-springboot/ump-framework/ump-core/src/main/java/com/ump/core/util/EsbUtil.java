package com.ump.core.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.ump.commons.esb.EsbCommunicator;
import com.ump.core.config.EsbCfgProperties;

public class EsbUtil {
	@Autowired
	private static EsbCfgProperties esbCfgProps;

	private EsbUtil() {
	}

	public static String callEsbService(String reqXml) {
		String host = esbCfgProps.getEsbIp();
		int port = esbCfgProps.getEsbPort();
		int timeout = esbCfgProps.getEsbTimeout();
		int connTimeout = esbCfgProps.getConnTimeout();
		EsbCommunicator esbCommunicator = new EsbCommunicator(host, port, timeout, connTimeout);
		return esbCommunicator.requestMessage(reqXml);
	}
}
