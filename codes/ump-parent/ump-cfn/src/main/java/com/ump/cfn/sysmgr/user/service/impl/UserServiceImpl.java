package com.ump.cfn.sysmgr.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.ump.exception.BusinessException;

import com.ump.cfn.sysmgr.role.model.Role;
import com.ump.cfn.sysmgr.user.dao.UserDao;
import com.ump.cfn.sysmgr.user.model.User;
import com.ump.cfn.sysmgr.user.service.UserService;
import com.ump.cfn.util.exception.ErrorCode;
import com.ump.core.base.service.BaseServiceImpl;
import com.ump.core.util.web.StatusCode;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findUserByUserCode(String username) throws BusinessException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode", username);
		try {
			return userDao.queryUser(paramMap);
		} catch (DataAccessException e) {
			logger.error(ErrorCode.E_1001.getMessage(), e);
			throw new BusinessException(ErrorCode.E_1001);
		}
	}

	@Override
	public StatusCode login(String username, String password, String verifyCode) throws BusinessException {
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

	@Override
	public User findById(String pid) throws BusinessException {
		try {
			return userDao.findById(pid);
		} catch (DataAccessException e) {
			throw new BusinessException("数据库删除失败", e);
		}
	}

}
