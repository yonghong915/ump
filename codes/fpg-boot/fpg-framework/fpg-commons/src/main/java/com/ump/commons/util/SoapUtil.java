package com.ump.commons.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SoapUtil {
	/**
	 * <p>
	 * Description: 根据请求报文，请求服务地址获取 响应报文
	 * 
	 * @param requestSoap    请求报文
	 * @param serviceAddress 响应报文
	 * @param charSet        字符集
	 * @param contentType    类型
	 * @return map封装的 服务器响应参数和返回报文.PS:statusCode :200正常响应。responseSoap：响应报文
	 *         <p>
	 *         thinking:
	 *         </p>
	 *
	 * @author huoge </span>
	 */
	public static String responseSoap(String requestSoap, String serviceAddress, String charSet, String contentType) {
		String soapRsp = "";
		String serviceUrl = "http://localhost:8888/pcms/serv";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String serviceName = "PdLine";
		String url = serviceUrl + "/" + serviceName;
		CloseableHttpResponse response = null;
		HttpPost httpPost = new HttpPost(url);
		HttpHost httpHost = new HttpHost("localhost", 8888);

		StringEntity entity = new StringEntity(requestSoap, StandardCharsets.UTF_8);
		entity.setContentEncoding(StandardCharsets.UTF_8.name());
		entity.setContentType("application/xml");
		httpPost.setEntity(entity);
		int statusCode = 0;
		try {
			response = httpClient.execute(httpHost, httpPost);
			statusCode = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				soapRsp = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			} else {
				throw new RuntimeException("请求失败：" + statusCode);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return soapRsp;
	}
}
