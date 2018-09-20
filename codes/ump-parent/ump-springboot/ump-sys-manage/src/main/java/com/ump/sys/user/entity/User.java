package com.ump.sys.user.entity;

import com.ump.core.base.entity.BaseObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author fangyh
 * @date 2018-08-09 21:43:09
 * @version 1.0.0
 */
public class User extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/***/
	@Setter
	@Getter
	private Integer userId;

	/***/
	@Setter
	@Getter
	private String userName;

	@Setter
	@Getter
	private String userPwd;

	@Setter
	@Getter
	private String realName;

	/***/
	@Setter
	@Getter
	private String userDesc;

	@Setter
	@Getter
	private String salt;

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof User)) {
			return false;
		}
		User user = (User) o;
		return user.userId.equals(userId) && user.userName.equals(userName) && user.userPwd.equals(userPwd);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + userId.hashCode();
		result = 31 * result + userName.hashCode();
		result = 31 * result + userPwd.hashCode();
		return result;
	}
}
