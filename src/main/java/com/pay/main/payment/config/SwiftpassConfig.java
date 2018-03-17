package com.pay.main.payment.config;

import com.pay.main.payment.util.PropertyUtil;

/**
 * 威富通支付配置信息
 * 
 * @author Guo
 */
public class SwiftpassConfig {
	// 威富通请求url
    public static String req_url;
    // 回调地址
    public static String notify_url;
    public static String merId;
    public static String Key;

	static {
    	PropertyUtil proper = PropertyUtil.getInstance("properties/pay");
		req_url = proper.getProperty("swiftpass.req_url").trim();
		notify_url = proper.getProperty("swiftpass.notify_url").trim();
		merId = proper.getProperty("swiftpass.merId").trim();
		Key = proper.getProperty("swiftpass.Key").trim();
	}
}