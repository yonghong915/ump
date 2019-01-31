package com.ump.uias.service;

import java.util.List;

import com.ump.uias.entity.User;

public interface IUserService {
	User get(String userId);

	List<User> selectAll();
}
