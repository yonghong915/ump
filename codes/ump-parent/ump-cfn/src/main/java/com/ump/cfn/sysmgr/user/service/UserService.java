package com.ump.cfn.sysmgr.user.service;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.core.util.web.StatusCode;

public interface UserService {

	User findUserByUserCode(String username);

	StatusCode login(String username, String password, String verifyCode);

}
