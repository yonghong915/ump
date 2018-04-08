package com.ump;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ump.exception.BusinessException;

import com.ump.cfn.sysmgr.permission.model.Permission;
import com.ump.cfn.sysmgr.permission.service.PermissionService;
import com.ump.cfn.sysmgr.resource.model.Resource;
import com.ump.cfn.sysmgr.resource.service.ResourceService;
import com.ump.cfn.sysmgr.role.model.Role;
import com.ump.cfn.sysmgr.role.service.RoleService;
import com.ump.cfn.sysmgr.user.model.User;
import com.ump.cfn.sysmgr.user.service.UserService;

public class TestSpring {
	static ApplicationContext appCtx = null;

	public static void main(String[] args) {
		String[] configLocations = new String[] { "config/spring/applicationContext-common.xml",
				"config/spring/applicationContext-datasource.xml", "config/spring/applicationContext-mybatis.xml",
				"config/spring/applicationContext-tx.xml" };
		System.out.println(System.getProperty("user.dir"));
		appCtx = new ClassPathXmlApplicationContext(configLocations);
		testRole();
		testResource();
	}

	public static void testResource() {
		ResourceService srv = (ResourceService) appCtx.getBean("resourceService");
		List<Resource> res = srv.findResourcesByUserName("fangyh");
		System.out.println(res.size());
	}

	public static void testPermission() {
		PermissionService srv = (PermissionService) appCtx.getBean("permissionService");
		Permission po = srv.findById("123456");
		System.out.println(po.getPermissionId());
	}

	public static void testRole() {

		RoleService srv = (RoleService) appCtx.getBean("roleService");
		try {
			List<Role> roles = srv.findRolesByUserName("fangyh");
			System.out.println(roles.size());
		} catch (BusinessException e) {
			System.out.println("aaaaa=" + e.getMsg());
		}
	}

	public void testUser() {
		UserService srv = (UserService) appCtx.getBean("userService");
		try {
			// User usr = srv.findById("123456");
			User usr = srv.findUserByUserCode("fangyh");
			System.out.println(usr.getPid());
			List<Role> roleList = usr.getRoles();
			System.out.println("role size:" + roleList.size());
			for (Role role : roleList) {
				System.out.println("roleName=" + role.getRoleName());
			}
		} catch (BusinessException e) {
			System.out.println("aaaaa=" + e.getMsg());
		}
	}
}
