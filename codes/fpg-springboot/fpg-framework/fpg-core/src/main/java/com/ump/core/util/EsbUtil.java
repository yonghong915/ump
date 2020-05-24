package com.ump.core.util;

import java.util.HashMap;
import java.util.Map;

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

	@SuppressWarnings("unchecked")
	public Map<String, Object> getEsbGlobalHeader(Map<String, Object> soapMap) {

		Map<String, Object> header = (Map<String, Object>) soapMap.get("Header");
		Map<String, Object> rspHeader = new HashMap<>();
		Map<String, Object> result = new HashMap<>();
		result.put("header", header);
		rspHeader.put("RspHeader", result);
		return rspHeader;
	}
}
