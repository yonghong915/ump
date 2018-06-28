package com.ump.cfn.sysmgr.user.dao;

import java.util.Map;

import org.ump.exception.DaoException;

import com.ump.cfn.sysmgr.user.model.User;
import com.ump.core.base.dao.BaseDao;

public interface UserDao extends BaseDao<User> {
	User queryUser(Map<String, Object> paramMap) throws DaoException;
}
