package com.ump.cfn.sysmgr.user.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author fangyh
 *
 */
public class User {
	@Getter
	@Setter
	private String id;

	@Getter
	@Setter
	private String username;

	@Getter
	@Setter
	private String userCode;
	@Getter
	@Setter(AccessLevel.PROTECTED)
	private String userPwd;
	@Getter
	@Setter(AccessLevel.PROTECTED)
	private String salt;
}
