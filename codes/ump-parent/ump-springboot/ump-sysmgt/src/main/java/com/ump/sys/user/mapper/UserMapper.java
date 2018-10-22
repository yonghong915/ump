package com.ump.sys.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ump.core.base.dao.BaseDao;
import com.ump.sys.user.entity.User;

/**
 * User ORM
 * 
 * @author fangyh
 * @date 2018-08-09 21:30:09
 * @version 1.0.0
 */
@Mapper
public interface UserMapper extends BaseDao<User> {
}
