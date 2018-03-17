package com.pay.main.payment.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProcessUtil {
	/**
	 * map.toString 转换成Map
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, String> mapStringToMap(String str) {
		str = str.substring(1, str.length() - 1);
		String[] strs = str.split(",");
		Map<String, String> map = new HashMap<String, String>();
		for (String string : strs) {
			String key = string.split("=")[0];
			String value = string.split("=")[1];
			map.put(key, value);
		}
		return map;
	}
	
	/**
	 * Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> convertBeanToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		return convertBean2Map(obj, false);
	}
	
	public static Map<String, Object> convertBeanNotNullToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		return convertBean2Map(obj, true);
	}
	
	private static Map<String, Object> convertBean2Map(Object obj,boolean bool) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if (!bool || (null != value && !"".equals(value))) {
						map.put(key, value);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}
	
	/**
	 * 拼接成字符串,map排序
	 * 
	 * @param params
	 * @param urlEncoding
	 * @return
	 */
	public static String textLink(Map<String, Object> params, boolean urlEncoding) {
		List<String> keys = new ArrayList<String>(params.keySet());
		StringBuilder sb = new StringBuilder();
		Collections.sort(keys);
		for (String key : keys) {
			sb.append(key).append("=");
			if (urlEncoding) {
				sb.append(urlEncode(params.get(key).toString()));
			} else {
				sb.append(params.get(key).toString());
			}
			sb.append("&");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
    
	/**
	 * URLEncode
	 * 
	 * @param str
	 * @return
	 */
	public static String urlEncode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (Throwable e) {
			return str;
		}
	}
	
	public static String jyMapValue(Object obj) {
		if (null != obj) {
			return obj + "";
		}
		return "";
	}
	
	public static Map<String, Object> removeNullMap(Map<String, Object> map) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		for (String key : map.keySet()) {
			String obj = map.get(key) + "";
			if (!"".equals(obj.trim()) && !"null".equals(obj)) {
				rtn.put(key, obj);
			}
		}
		return rtn;
	}
	
	public static String changeF2Y(String amount) throws Exception {
		if (!amount.matches("\\-?[0-9]+")) {
			throw new Exception("金额格式有误");
		}
		return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();
	}  
	
	/**   
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）  
     *   
     * @param amount  
     * @return  
     * @throws Exception   
     */    
	public static String changeF2Y(Long amount) throws Exception {
		if (!amount.toString().matches("\\-?[0-9]+")) {
			throw new Exception("金额格式有误");
		}

		int flag = 0;
		String amString = amount.toString();
		if (amString.charAt(0) == '-') {
			flag = 1;
			amString = amString.substring(1);
		}
		StringBuffer result = new StringBuffer();
		if (amString.length() == 1) {
			result.append("0.0").append(amString);
		} else if (amString.length() == 2) {
			result.append("0.").append(amString);
		} else {
			String intString = amString.substring(0, amString.length() - 2);
			for (int i = 1; i <= intString.length(); i++) {
				if ((i - 1) % 3 == 0 && i != 1) {
					result.append(",");
				}
				result.append(intString.substring(intString.length() - i,
						intString.length() - i + 1));
			}
			result.reverse().append(".")
					.append(amString.substring(amString.length() - 2));
		}
		if (flag == 1) {
			return "-" + result.toString();
		} else {
			return result.toString();
		}
	}

	/**
	 * 将元为单位的转换为分 （乘100）
	 * 
	 * @param amount
	 * @return
	 */
	public static String changeY2F(Long amount) {
		return BigDecimal.valueOf(amount).multiply(new BigDecimal(100))
				.toString();
	}

	/**
	 * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
	 * 
	 * @param amount
	 * @return
	 */
	public static String changeY2F(String amount) {
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
																// 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
					".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
					".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
					".", "") + "00");
		}
		return amLong.toString();
	}
}