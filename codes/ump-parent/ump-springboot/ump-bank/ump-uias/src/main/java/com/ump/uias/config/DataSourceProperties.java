package com.ump.uias.config;

import java.io.Serializable;
import lombok.Data;

@Data
public class DataSourceProperties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type;

	private String driverClassName;

	private String url;

	private String username;

	private String password;
}
