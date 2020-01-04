/*
 *Copyright (C) 2019 FangYH.All rights reserved.
 */
package com.ump.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author fangyh
 * @version 1.0.0
 * @since 1.0.0
 * @date 2019-12-21 11:25:43
 *
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        System.out.println("JwtUserDetailsService:" + user);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new SecurityUserDetails(user,authorityList);
    }

}
