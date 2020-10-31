/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.ws;

import com.ump.core.ws.config.WebServiceInterfaceManager;
import com.ump.core.ws.entity.WebServiceInterfaceInfo;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-08-08 21:10:05
 *
 */
public class TestWs {

	public static void main(String[] args) {
		String wsdlPath = "F:\\baiduyunpan\\studyData\\specialSubject\\BlockChain\\project\\ump\\codes\\ump-parent\\ump-springboot\\ump-framework\\ump-core\\src\\main\\resources\\config\\wsdl_config\\S090000001\\S090000001.wsdl";
		// ServiceInfo serviceInfo = WebServiceInterfaceManager.getServiceInfoByWsdl(wsdlPath);
		// System.out.println("serviceInfo=" + serviceInfo);
		String operation = "doService";
		WebServiceInterfaceInfo interfaceInfo = WebServiceInterfaceManager.getInterfaceInfo(wsdlPath, operation);
		System.out.println(interfaceInfo);
	}

}
