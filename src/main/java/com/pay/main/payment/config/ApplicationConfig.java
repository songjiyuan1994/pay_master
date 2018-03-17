package com.pay.main.payment.config;

import com.pay.main.payment.util.PropertyUtil;

/**
 * 全局配置文件信息
 * 
 * @author Guo
 */
public class ApplicationConfig {
	private static PropertyUtil proper = PropertyUtil.getInstance("properties/application");

	public static String KAFKA_URL = proper.getProperty("kafka.url");
}