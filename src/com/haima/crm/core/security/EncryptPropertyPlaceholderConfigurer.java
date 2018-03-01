package com.haima.crm.core.security;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 将db配置文件中的密文解析
 * @author haima
 * @packageName com.bonclan.core.db
 * @fileName EncryptPropertyPlaceholderConfigurer.java
 * @projectName 03-cms
 * @date 2018-1-3   下午2:51:25
 */
public class EncryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	private String[] encryptPropNames = { "jdbc.username", "jdbc.pwd" };

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			String decryptValue = AESUtil.decrypt(propertyValue);
			return decryptValue;
		} else {
			return propertyValue;
		}
	}

	/**
	 * 判断是否是加密的属性
	 */
	private boolean isEncryptProp(String propertyName) {
		for (String encryptPropName : encryptPropNames) {
			if (encryptPropName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}
}
