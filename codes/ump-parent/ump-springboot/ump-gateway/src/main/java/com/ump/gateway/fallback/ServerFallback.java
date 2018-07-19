package com.ump.gateway.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.exception.HystrixTimeoutException;

/**
 * 向服务路由发起请求失败时的回滚处理 hystrix的回滚能力
 * 
 * @author fangyh
 *
 */

@Component
public class ServerFallback implements FallbackProvider {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String getRoute() {
		return "*";// api服务id，如果需要所有调用都支持回退，则return "*"或return null
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		logger.info("Service route hystrix fallback,route={},route exception={}", route, cause);
		if (cause instanceof HystrixTimeoutException) {
			return response(HttpStatus.GATEWAY_TIMEOUT);
		} else {
			return response(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
		}

	}

	private ClientHttpResponse response(HttpStatus status) {

		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK; // 请求网关成功了，所以是ok
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.OK.value();
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.OK.getReasonPhrase();
			}

			@Override
			public void close() {

			}

			@Override
			public InputStream getBody() throws IOException {
				JSONObject json = new JSONObject();
				json.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
				json.put("msg", HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
				return new ByteArrayInputStream(json.toJSONString().getBytes(StandardCharsets.UTF_8)); // 返回前端的内容
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8); // 设置头
				return httpHeaders;
			}
		};
	}

}
