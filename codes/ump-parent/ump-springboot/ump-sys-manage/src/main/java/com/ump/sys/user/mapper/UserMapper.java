package com.ump.sys.user.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.ump.core.base.dao.BaseDao;
import com.ump.sys.user.model.User;

@Mapper
public interface UserMapper extends BaseDao<User> {
	//User queryById(String userId);
}
