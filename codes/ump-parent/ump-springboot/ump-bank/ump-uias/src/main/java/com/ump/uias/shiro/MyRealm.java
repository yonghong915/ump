package com.ump.uias.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ump.commons.constant.ConstantUtil;
import com.ump.uias.modules.system.entity.SysResource;
import com.ump.uias.modules.system.entity.SysRole;
import com.ump.uias.modules.system.entity.SysUser;
import com.ump.uias.modules.system.service.ISysUserService;

/**
 * 
 * @author fangyh
 * @version 1.0
 * @since 1.0
 */
@Component
public class MyRealm extends AuthorizingRealm {
	@Autowired
	private ISysUserService userService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	/**
	 * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = JWTUtil.getUsername(principals.toString());
		SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
		// 添加角色和权限
		List<SysRole> roles = userService.queryRolesByUserName(username);
		roles.forEach(role -> {
			authInfo.addRole(role.getRoleCode());
		});

		List<SysResource> resources = userService.queryResourcesByUserName(username);
		resources.forEach(res -> {
			authInfo.addStringPermission(res.getResourceUrl());
		});
		return authInfo;
	}

	/**
	 * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		String token = (String) authToken.getCredentials();
		if (token == null) {
			throw new AuthenticationException("token为空!");
		}
		// 解密获得username，用于和数据库进行对比
		String username = JWTUtil.getUsername(token);
		if (username == null) {
			throw new AuthenticationException("token无效");
		}

		SysUser user = userService.queryUserByUserName(username);
		if (user == null) {
			throw new AuthenticationException("用户不存在!");
		}

		if (!JWTUtil.verify(token, username, user.getUserPwd() + "{"  + "}")) {
			throw new AuthenticationException("用户名或密码错误");
		}

		if (ConstantUtil.UserStatus.LOCKED.getValue().equals(user.getUserStatus())) {
			throw new AuthenticationException("账号已被锁定,请联系管理员!");
		}
		// DisabledAccountException UnknownAccountException
		return new SimpleAuthenticationInfo(token, token, getName());
	}
}