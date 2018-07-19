package com.ump.gateway.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * 向用户管理api-user-server路由发起请求失败时的回滚处理 hystrix的回滚能力
 * 
 * @author fangyh
 *
 */

@Component
public class ServerFallback implements FallbackProvider {
	@Override
	public String getRoute() {
		return "*";// api服务id，如果需要所有调用都支持回退，则return "*"或return null
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {

		return null;
	}

}
