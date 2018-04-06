package com.ump;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ump.exception.BusinessException;

import com.ump.cfn.sysmgr.role.model.Role;
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
		// UserService srv = (UserService) appCtx.getBean("userService");
		UserService srv = (UserService) appCtx.getBean("userService");
		try {
			//User usr = srv.findById("123456");
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
		
//		RoleService srv = (RoleService) appCtx.getBean("roleService");
//		try {
//			Role usr = srv.findById("123456");
//			System.out.println(usr.getPid());
//			List<Permission> roleList = usr.getPermissions();
//			System.out.println("role size:" + roleList.size());
//			for (Permission p : roleList) {
//				System.out.println("roleName=" + p.getPermissionName());
//			}
//		} catch (BusinessException e) {
//			System.out.println("aaaaa=" + e.getMsg());
//		}
	}

}
