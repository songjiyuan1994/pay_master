package com.pay.main.payment.util;

import java.util.HashMap;
import java.util.Map;

public class ReturnUtils {
	/**
	 * 返回值
	 * 
	 * @param index
	 * @param info
	 * @return
	 */
	public static String getRtnString(Integer index, String info) {
		return "{\"state\":" + index + ",\"info\":\"" + info + "\"}";
	}

	public static Map<String, Object> successInfo() {
		return successInfo(null);
	}

	public static Map<String, Object> failInfo() {
		return failInfo(null);
	}

	public static Map<String, Object> successInfo(Object info) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("state", 200);
		if (null != info) {
			rtnMap.put("data", info);
		}
		return rtnMap;
	}

	public static Map<String, Object> failInfo(Object info) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("state", 400);
		if (null != info) {
			rtnMap.put("data", info);
		}
		return rtnMap;
	}
}