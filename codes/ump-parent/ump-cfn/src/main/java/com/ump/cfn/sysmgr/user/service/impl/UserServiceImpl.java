package com.ump.cfn.sysmgr.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ump.exception.DaoException;
import org.ump.exception.ServiceException;

import com.ump.cfn.sysmgr.user.dao.UserDao;
import com.ump.cfn.sysmgr.user.model.User;
import com.ump.cfn.sysmgr.user.service.UserService;
import com.ump.core.base.service.BaseServiceImpl;
import com.ump.core.util.web.StatusCode;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findUserByUserCode(String username) throws ServiceException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode", username);
		try {
			return userDao.queryUser(paramMap);
		} catch (DaoException e) {
			logger.error("", e);
			throw new ServiceException("", e);
		}
	}

	@Override
	public StatusCode login(String username, String password, String verifyCode) throws ServiceException {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		StatusCode sc = StatusCode.SUCCESS;
		UsernamePasswordToken token = new UsernamePasswordToken(username, password.toUpperCase());

		session.getAttribute("");
		if (!currentUser.isAuthenticated()) {
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				logger.error("aaa", e);
			}
		}
		if (!currentUser.isAuthenticated()) {
			token.clear();
		}

		return sc;
	}

}
