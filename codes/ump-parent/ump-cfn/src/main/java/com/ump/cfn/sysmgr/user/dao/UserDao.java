package com.ump.cfn.sysmgr.user.dao;

import java.util.Map;

import org.ump.exception.DaoException;

import com.ump.cfn.sysmgr.user.model.User;

public interface UserDao {
	User queryUser(Map<String, Object> paramMap) throws DaoException;
}
