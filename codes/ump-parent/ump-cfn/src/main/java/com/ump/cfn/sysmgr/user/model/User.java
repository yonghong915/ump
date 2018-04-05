package com.ump.cfn.sysmgr.user.model;

import com.ump.core.base.model.BaseObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author fangyh
 *
 */
public class User extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6262747901732584984L;

	@Getter
	@Setter
	private String userName;

	@Getter
	@Setter
	private String userCode;

	@Getter
	@Setter
	private String userPwd;

	@Getter
	@Setter
	private String userDesc;

	@Getter
	@Setter
	private String salt;
}
