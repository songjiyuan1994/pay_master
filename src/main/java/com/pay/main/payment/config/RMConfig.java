package com.pay.main.payment.config;

import com.pay.main.payment.util.PropertyUtil;

/**
 * 融脉支付配置信息
 * 
 * @author Guo
 */
public class RMConfig {
	// 融脉商户
	public static final String INFO_VIRACCNO = "0"; // 收款账户
	public static final String INFO_TRADETYPE = "cs.pay.submit"; // 交易类型
	public static final String INFO_VERSION = "2.0"; // 版本
	public static final String INFO_CHANNEL_APP = "wx_app"; // 支付使用的三方支付渠道
	public static final String INFO_CHANNEL_SCAN = "wx_qr"; // 扫码支付
	public static final String INFO_CHANNEL_PUB = "wx_pub"; // 公众号支付
	public static final String INFO_CHANNEL_WAP = "wx_wap"; // 公众号支付
	public static final String INFO_CHANNEL_H5 = "payh5"; // 公众号支付
	
	public static final String INFO_CHANNEL_ALAPP = "ali_app"; //
	public static final String INFO_CHANNEL_ALSCAN = "ali_qr"; //
	public static final String INFO_CHANNEL_ALWAP = "ali_wap"; //
	
	// 融脉下单和回调地址
	public static String PRODUCE_URL;
	public static String NOTIFY_URL;
	public static String RM_URL_WECHAT_PUB;
	
	static {
		PropertyUtil proper = PropertyUtil.getInstance("properties/pay");
		PRODUCE_URL = proper.getProperty("rm.produce_url");
		NOTIFY_URL = proper.getProperty("rm.notify_url");
		RM_URL_WECHAT_PUB = proper.getProperty("rm.wechat_pub_url");
	}
}