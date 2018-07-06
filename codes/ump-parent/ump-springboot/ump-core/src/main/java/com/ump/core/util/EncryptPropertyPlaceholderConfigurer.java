package com.ump.core.util;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.ump.commons.crypto.DESUtils;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private Set<String> encryptedProps = Collections.emptySet();

	public void setEncryptedProps(Set<String> encryptedProps) {
		this.encryptedProps = encryptedProps;
	}

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {

		if (encryptedProps.contains(propertyName)) { // 如果在加密属性名单中发现该属性

			String rtnValue = DESUtils.decrypt(propertyValue);
			return rtnValue;
			// final Matcher matcher = encryptedPattern.matcher(propertyValue);
			// // 判断该属性是否已经加密
			// if (matcher.matches()) { // 已经加密，进行解密
			// String encryptedString = matcher.group(1); // 获得加密值
			// String decryptedPropValue = "";// AesUtils.decrypt(propertyName +
			// SEC_KEY, encryptedString); // 调用AES进行解密，SEC_KEY与属性名联合做密钥更安全
			//
			// if (decryptedPropValue != null) { // !=null说明正常
			// propertyValue = decryptedPropValue; // 设置解决后的值
			// } else {// 说明解密失败
			// logger.error("Decrypt " + propertyName + "=" + propertyValue + "
			// error!");
			// }
			// }
		}

		return super.convertProperty(propertyName, propertyValue); // 将处理过的值传给父类继续处理
	}
}
