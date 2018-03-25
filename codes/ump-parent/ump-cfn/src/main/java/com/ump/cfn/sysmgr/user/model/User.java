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
	private String userId;

	@Getter
	@Setter(AccessLevel.PROTECTED)
	private String userPwd;
}
