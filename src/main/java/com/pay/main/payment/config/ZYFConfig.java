package com.pay.main.payment.config;

import com.pay.main.payment.util.PropertyUtil;

/**
 * 威富通支付配置信息
 * 
 * @author Guo
 */
public class ZYFConfig {
    public static String req_url;
    public static String merId;
    public static String Key;
    public static String AppId;

	static {
    	PropertyUtil proper = PropertyUtil.getInstance("properties/pay");
		req_url = proper.getProperty("zyf.req_url").trim();
		merId = proper.getProperty("zyf.merId").trim();
		Key = proper.getProperty("zyf.Key").trim();
		AppId = proper.getProperty("zyf.appId").trim();
	}
}