package com.ump.cfn.user.service;

import com.ump.cfn.user.model.User;

public interface UserService {

	User findUserByUserCode(String username);

}
