package com.ump.cfn.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ump.exception.BusinessException;

import com.ump.cfn.sysmgr.resource.service.ResourceService;
import com.ump.cfn.sysmgr.role.service.RoleService;
import com.ump.cfn.sysmgr.user.model.User;
import com.ump.cfn.sysmgr.user.service.UserService;
import com.ump.commons.CommUtils;
import com.ump.commons.crypto.CipherUtils;

/**
 * 
 * @author fangyh
 * @date 2017-04-23 19:55:05
 * @version 1.0
 */
@Service
public class ShiroRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	/**
	 * 权限认证: 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
	 */

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 直接调用getPrimaryPrincipal得到之前传入的用户名
		String username = (String) principals.getPrimaryPrincipal();
		logger.info("[用户:" + username + "|权限授权]");
		String loinname = (String) principals.fromRealm(getName()).iterator().next();
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principals);
			SecurityUtils.getSubject().logout();
			return null;
		}
		SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
		// 根据用户名调用UserService接口获取角色及权限信息
		authInfo.setRoles(roleService.loadRoleIdByUsername(username));
		authInfo.setStringPermissions(resourceService.loadPermissionsByUsername(username));
		logger.info("[用户:" + username + "|权限授权完成]");
		return authInfo;
	}

	/**
	 * 登录认证:登录信息和用户验证信息验证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		AuthenticationInfo authenticationInfo = null;
		String username = token.getUsername();// 用户名
		// String username = (String) token.getPrincipal();
		System.out.println("pwd:" + token.getCredentials().toString());
		logger.info("[用户:" + username + "|系统权限认证]");
		if (CommUtils.isEmpty(username)) {
			throw new BusinessException("");
		}
		String password = new String(token.getPassword());// 密码
		User user = userService.findUserByUserCode(username);
		if (null == user) {
			throw new UnknownAccountException("");
		}

		String userPwd = user.getUserPwd();
		if (CommUtils.isEmpty(userPwd)) {
			throw new BusinessException("");
		}
		/* 组合username,两次迭代，对密码进行加密 */
		// String pwdEncrypt = CipherUtils.createEncryptPwd(username, password,
		// user.getSalt());
		String pwdEncrypt = "123456";
		if (userPwd.equals(pwdEncrypt)) {
			authenticationInfo = new SimpleAuthenticationInfo(user.getUserCode(), user.getUserPwd(), getName());
			// authenticationInfo = new SimpleAuthenticationInfo(username, password,
			// getName());
			this.setSession("session_user", user);

			// ByteSource.Util.bytes(sqluser.getCredentialsSalt()), this.getName());// realm
			logger.info("[用户:" + username + "|系统权限认证完成]");
			return authenticationInfo;
		} else {
			throw new IncorrectCredentialsException("");
		}
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