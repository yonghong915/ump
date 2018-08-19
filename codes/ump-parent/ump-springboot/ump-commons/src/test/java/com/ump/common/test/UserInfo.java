package com.ump.common.test;

import java.io.Serializable;

import desensitization.Desensitized;
import desensitization.SensitiveType;
import lombok.Getter;
import lombok.Setter;

public class UserInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Desensitized(type = SensitiveType.CHINESE_NAME)
	@Getter
	@Setter
	private String realName;

	@Desensitized(type = SensitiveType.ID_CARD)
	@Getter
	@Setter
	private String idCardNo;

	@Desensitized(type = SensitiveType.MOBILE_PHONE)
	@Getter
	@Setter
	private String mobileNo;

	@Getter
	@Setter
	private String account;

	@Desensitized(type = SensitiveType.PASSWORD, isEffictiveMethod = "isEffictiveMethod")
	@Getter
	@Setter
	private String password;

	@Getter
	@Setter
	private boolean effictiveMethod = true;

}
