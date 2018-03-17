package com.pay.main.payment.util;


import com.pay.main.payment.entity.UserLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtils {
	public static String LOGIN_USERINFO = "LOGIN_USERINFO";

	public static String LOGIN_CAPTCHA = "LOGIN_CAPTCHA";

	public static UserLogin getUser(HttpServletRequest request) {
		// 获取登陆信息
		HttpSession session = request.getSession();
		UserLogin userlogin = null != session.getAttribute(LOGIN_USERINFO) ? (UserLogin) session.getAttribute(LOGIN_USERINFO) : null;
		return userlogin;
	}

	/**
	 * 获取真实IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}