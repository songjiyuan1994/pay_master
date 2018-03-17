package com.pay.main.payment.util;

import java.util.HashMap;
import java.util.Map;

public class ReturnUtil {
	public static final String RETURN_SUCCESS = "1000";
	public static final String RETURN_FAIL = "1099";

	public static Map<String, Object> returnInfo(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != obj) {
			map.put("rtnCode", RETURN_SUCCESS);
			map.put("rtnMsg", obj);
		} else {
			map.put("rtnCode", RETURN_FAIL);
			map.put("rtnMsg", "其他错误！");
		}
		return map;
	}

	public static Map<String, Object> returnFail() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rtnCode", RETURN_FAIL);
		map.put("rtnMsg", "其他错误！");
		return map;
	}

	public static Map<String, Object> returnFail(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rtnCode", RETURN_FAIL);
		map.put("rtnMsg", "其他错误！");
		return map;
	}
	public static Map<String, Object> returnFail(Object obj,int errCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rtnCode", errCode);
		map.put("rtnMsg", obj);
		return map;
	}
}
