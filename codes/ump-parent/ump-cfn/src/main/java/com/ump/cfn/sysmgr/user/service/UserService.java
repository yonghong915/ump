package com.ump.cfn.sysmgr.user.service;

import com.ump.cfn.sysmgr.user.model.User;

public interface UserService {

	User findUserByUserCode(String username);

}
