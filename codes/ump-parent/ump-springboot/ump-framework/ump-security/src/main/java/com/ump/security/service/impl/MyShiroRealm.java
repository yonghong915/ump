//package com.ump.security.service.impl;
//
//import java.util.Set;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.util.ByteSource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.google.common.collect.Sets;
//import com.ump.commons.CommUtils;
//import com.ump.commons.exception.BusinessException;
//import com.ump.security.shiro.authc.JwtToken;
//import com.ump.uias.entity.User;
//import com.ump.uias.service.IUserService;
//
///**
// * 
// * @author fangyh
// * @date 2018-09-19 11:12:19
// * @version 1.0.0
// */
//public class MyShiroRealm extends AuthorizingRealm {
//	private Logger logger = LoggerFactory.getLogger(getClass());
//
//	private Boolean cachingEnabled = Boolean.TRUE;
//	@Autowired
//	private IUserService userService;
//
//	/**
//	 * 必须重写此方法，不然Shiro会报错
//	 */
//	@Override
//	public boolean supports(AuthenticationToken token) {
//		return token instanceof JwtToken;
//	}
//
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		logger.info("————权限认证 [ roles、permissions]————");
//		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
//		Set permissions = Sets.newHashSet();
//		simpleAuthorInfo.addStringPermissions(permissions);
//		return simpleAuthorInfo;
//	}
//
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
//		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
//		SimpleAuthenticationInfo authenticationInfo = null;
//		String userName = token.getUsername();
//		if (CommUtils.isEmpty(userName) || "unknown".equals(userName)) {
//			throw new UnknownAccountException("登录用户名为空值");
//		}
//		String password = new String(token.getPassword());// 密码
//		if (CommUtils.isEmpty(password)) {
//			throw new BusinessException("E00001", "登录密码为空值");
//		}
//		User user = userService.queryUserByUserCode(userName);
//		if (null == user) {
//			throw new BusinessException("E0022", "用户【" + userName + "】不存在");
//		}
//		String userPwd = user.getUserPwd();
//		if (CommUtils.isEmpty(userPwd)) {
//			throw new BusinessException("E0003", "系统存储密码为空值");
//		}
//		authenticationInfo = new SimpleAuthenticationInfo(user, user.getUserPwd(), getName());
//		super.clearCachedAuthorizationInfo(authenticationInfo.getPrincipals());
//		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
//		this.setSession("sessionUser", user);
//		logger.info("[用户:" + userName + "|系统权限认证完成]");
//		return authenticationInfo;
//	}
//
//	/**
//	 * 将一些数据放到ShiroSession中,以便于其它地方使用
//	 *
//	 * @see
//	 */
//	private void setSession(Object key, Object value) {
//		Subject currentUser = SecurityUtils.getSubject();
//		if (null != currentUser) {
//			Session session = currentUser.getSession();
//			if (null != session) {
//				session.setAttribute(key, value);
//			}
//		}
//	}
//	
//}
