package com.ump.cfn.sysmgr.user.model;

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
	@Setter
	private String userPwd;
	@Getter
	@Setter
	private String salt;
}
