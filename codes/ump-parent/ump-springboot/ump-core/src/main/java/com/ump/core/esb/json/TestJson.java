package com.ump.core.esb.json;


import org.apache.commons.text.StringEscapeUtils;

import com.alibaba.fastjson.JSON;

public class TestJson {

	public static void main(String[] args) {
		AbstractController<UserReq, UserRsp> s = new AbstractController<>();
		String json;
		try {
			json = JSON.toJSONString(s.execute(null, null));
			System.out.println(json);
			
			String ss = StringEscapeUtils.escapeHtml4("<script>(123)(*&^%$#@!)</script>");
			System.out.println(ss);
			System.out.println(StringEscapeUtils.unescapeHtml4(ss));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
