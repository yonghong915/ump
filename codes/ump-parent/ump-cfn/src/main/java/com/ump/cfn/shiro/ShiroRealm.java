package com.ump.cfn.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ump.cfn.sysmgr.user.service.UserService;


/**
 * 
 * @author fangyh
 * @date 2017-04-23 19:55:05
 * @version 1.0
 */
@Service
public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	/**
	 * 登录信息和用户验证信息验证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		AuthenticationInfo authenticationInfo = null;
		String username = new String(token.getUsername());// 用户名
//		if (CommUtils.isEmpty(username)) {
//			throw new BusinessException(StatusCode.USER_NAME_EMPTY.code());
//		}
//		String password = new String(token.getPassword());// 密码
//		User user = userService.findUserByUserCode(username);
//		if (null == user) {
//			throw new UnknownAccountException(StatusCode.USER_ACCT_NOT_FOUND.code());
//		}
//
//		String userPwd = user.getUserPwd();
//		if (CommUtils.isEmpty(userPwd)) {
//			throw new BusinessException(StatusCode.USER_PWD_EMPTY.code());
//		}
//		/* 组合username,两次迭代，对密码进行加密 */
//		String pwdEncrypt = CipherUtils.createEncryptPwd(username, password, user.getSalt());
//		if (userPwd.equals(pwdEncrypt)) {
//			authenticationInfo = new SimpleAuthenticationInfo(user.getUserCode(), password, getName());
//			this.setSession(Constants.SESSION_USER, user);
			return authenticationInfo;
//		} else {
//			throw new IncorrectCredentialsException(StatusCode.USER_ACCT_AHTH_EXCE.code()); /* 错误认证异常 */
//		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principals);
			SecurityUtils.getSubject().logout();
			return null;
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return info;
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 *
	 * @see
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}