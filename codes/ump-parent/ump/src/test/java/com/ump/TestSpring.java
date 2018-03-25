package com.ump;


import com.ump.commons.crypto.DESUtils;

public class TestSpring {

	public static void main(String[] args) {
		// ApplicationContext appCtx = null;
		// System.out.println(System.getProperty("user.dir"));
		// String userDir = System.getProperty("user.dir");
		// String[] configLocations = new String[] { userDir +
		// "/src/main/resources/config/spring/springmvc-servlet.xml"
		//
		// };
		//
		// appCtx = new FileSystemXmlApplicationContext(configLocations);
		//StatusCode sc = StatusCode.valueOf("SUCCESS");
		//System.out.println(sc.name()+" "+sc.code()+" "+sc.message());
		System.out.println(DESUtils.encrypt("umpdbuser"));
		System.out.println(DESUtils.encrypt("umpdbuser1234"));
		System.out.println(DESUtils.decrypt("vIJyoAVNfHu8emfVRoIAjw"));
	}

}
