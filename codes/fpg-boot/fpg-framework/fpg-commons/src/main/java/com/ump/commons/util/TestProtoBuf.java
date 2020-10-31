package com.ump.commons.util;

import java.util.Date;

public class TestProtoBuf {

	public static void main(String[] args) {
		User user = new User();
		user.setUserId(1);
		user.setUserTypeId(1);
		user.setUserName("XRQ");
		user.setCreateDateTime(new Date());
		//序列化成ProtoBuf数据结构
		byte[] userProtoObj= ProtoBufUtil.serializer(user);
		 
		//ProtoBuf数据结构反序列化成User对象
		User newUserObj = ProtoBufUtil.deserializer(userProtoObj, User.class);
        System.out.println(newUserObj.getUserId()+" "+newUserObj.getUserName());
	}

}
