package com.ump.uias.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ump.commons.constant.ConstantUtil;
import com.ump.core.base.datasource.DS;
import com.ump.uias.modules.system.entity.SysResource;
import com.ump.uias.modules.system.entity.SysRole;
import com.ump.uias.modules.system.entity.SysUser;
import com.ump.uias.modules.system.mapper.SysUserMapper;
import com.ump.uias.modules.system.service.ISysResourceService;
import com.ump.uias.modules.system.service.ISysUserRoleService;
import com.ump.uias.modules.system.service.ISysUserService;
import com.ump.uias.shiro.JWTUtil;

@CacheConfig(cacheNames = { "umpUserCache" })
@Service("userService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	//@Autowired
	//private SysUserMapper userMapper;

	@Autowired
	private ISysUserRoleService userRoleService;

	@Autowired
	private ISysResourceService resourceService;

	@Override
	public SysUser get(String userId) {
		return this.getOne(new QueryWrapper(userId));
	}

	@Override
	public List<SysUser> selectAll() {
		List<SysUser> userList = this.list(null);
		return userList;
	}

	@Cacheable(value = "umpUserCache", key = "targetClass + methodName +#p0")
	@DS(value = ConstantUtil.DSType.DS_TYPE_SYSDB)
	@Override
	public SysUser queryUserByUserName(String userName) {
		SysUser entity = new SysUser();
		entity.setUserName(userName);
		List<SysUser> userList = this.baseMapper.queryUserList(entity);
		return (null != userList) ? userList.get(0) : null;
	}

	/**
	 * 用户登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public String jwtLogin(String username, String password) {
		Assert.notNull(username, "用户名不能为空");
		Assert.notNull(password, "密码不能为空");

		// 获取用户密码混淆值
		// MemberDTO member = memberService.getMemberSalt(username);

		// 加密当前用户输入密码
//		byte[] bytePassword = DigestUtil.sha1(password.getBytes(), EncodeUtils.hexDecode(member.getSalt()),
//				Constants.PASSWORD_HASH_INTERATIONS);
//		String encodePassword = EncodeUtils.hexEncode(bytePassword);
//
//		if (!encodePassword.equals(member.getLoginPassword())) {
//			throw new TSharkException("900");
//		}
		String encodePassword = "123456";
		return JWTUtil.sign(username, encodePassword);
	}

	@DS(ConstantUtil.DSType.DS_TYPE_SYSDB)
	@Override
	public List<SysRole> queryRolesByUserName(String username) {
		return userRoleService.queryRolesByUserName(username);
	}

	@Override
	public List<SysResource> queryResourcesByUserName(String username) {
		return resourceService.queryResourcesByUserName(username);
	}

}
