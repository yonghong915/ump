package com.ump.core.ws.entity;

import lombok.Data;

@Data
public class Parameter {

	private String name;

	private String inOutFlag;
	
	private String description;
	
	private String internalType;
}
