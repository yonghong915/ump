package com.ump.uias.entity;

import com.ump.core.base.entity.BaseObject;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

public class User extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiParam("用户编号")
	@Getter
	@Setter
	private String userId;

	@ApiParam("用户姓名")
	@Getter
	@Setter
	private String userName;

	@ApiParam("用户密码")
	@Getter
	@Setter
	private String userPwd;

	@ApiParam("用户描述")
	@Getter
	@Setter
	private String userDesc;
}
