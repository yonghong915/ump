package com.ump.cfn.shiro.authc.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import com.ump.commons.crypto.CipherUtils;

public class LimitRetryHashedMatcher extends HashedCredentialsMatcher {
	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		String username = (String) authcToken.getPrincipal();
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		SimpleAuthenticationInfo sinfo = (SimpleAuthenticationInfo) info;
		String salt = new String(sinfo.getCredentialsSalt().getBytes());
		String inputCredential = CipherUtils.encrypt4SHA256(username, String.valueOf(token.getPassword()), salt);

		String credentials = String.valueOf(getCredentials(info));
		// 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
		return equals(inputCredential, credentials);
	}
}
