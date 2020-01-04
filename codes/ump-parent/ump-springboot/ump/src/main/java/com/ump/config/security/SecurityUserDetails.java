/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ump.entity.sys.UserEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-21 11:21:51
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecurityUserDetails extends UserEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5742998074832506794L;
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public SecurityUserDetails(String userName, Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
		this.setUserName(userName);
		String encode = new BCryptPasswordEncoder().encode("123456");
		this.setUserPwd(encode);
		this.setAuthorities(authorities);
	}

	/**
	 * 账户是否过期
	 * 
	 * @return
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 是否禁用
	 * 
	 * @return
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 密码是否过期
	 * 
	 * @return
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 是否启用
	 * 
	 * @return
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}
