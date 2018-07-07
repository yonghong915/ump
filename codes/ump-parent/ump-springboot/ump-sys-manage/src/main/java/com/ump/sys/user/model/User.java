package com.ump.sys.user.model;

import com.ump.core.base.model.BaseObject;

import lombok.Getter;
import lombok.Setter;

public class User extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Setter
	@Getter
	private String userId;

	@Setter
	@Getter
	private String userName;

	@Setter
	@Getter
	private String userPwd;

	@Setter
	@Getter
	private String userDesc;
}
