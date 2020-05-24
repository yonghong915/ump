/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.core.ws.entity;

import lombok.Data;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-08-26 20:42:29
 *
 */
@Data
public class InterfaceInfo {
	private String wsdlUrl;
	
	private String name;

	private String namespaceURI;
	
	private String categoryId;
}
