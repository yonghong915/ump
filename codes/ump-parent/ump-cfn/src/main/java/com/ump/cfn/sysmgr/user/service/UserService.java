package com.ump.cfn.sysmgr.user.service;

import org.ump.exception.BusinessException;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.core.base.service.BaseService;
import com.ump.core.util.web.StatusCode;

public interface UserService extends BaseService<User> {

	User findUserByUserCode(String username) throws BusinessException;

	StatusCode login(String username, String password, String verifyCode) throws BusinessException;

}
