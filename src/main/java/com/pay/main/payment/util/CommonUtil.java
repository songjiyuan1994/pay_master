package com.pay.main.payment.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {
	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);



	/**
	 * 获取用户IP（Linux系统有问题）
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = null;
		try {
			ip = request.getHeader("X-Forwarded-For");
			if (null != ip && !"".equals(ip) && !"unKnown".equalsIgnoreCase(ip)) {
				// 多次反向代理后会有多个ip值，第一个ip才是真实ip
				int index = ip.indexOf(",");
				if (index != -1) {
					return ip.substring(0, index);
				} else {
					return ip;
				}
			}
			ip = request.getHeader("X-Real-IP");
			if (null != ip && !"".equals(ip) && !"unKnown".equalsIgnoreCase(ip)) {
				return ip;
			}
			return request.getRemoteAddr();
		} catch (Exception ex) {
			logger.error("获取用户IP：", ex);
		}
		return null;
	}

	/**
	 * 根据下单规则传值，返回订单号
	 */
	public static String getOrderNo() {
		return DataFormatUtil.getONLYID32();
	}
}