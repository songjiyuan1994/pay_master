package com.pay.main.payment.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PropertyUtil {
	private static Map<String, PropertyUtil> instance = Collections.synchronizedMap(new HashMap());
	private static Object lock = new Object();
	protected String sourceUrl;
	protected ResourceBundle resourceBundle;
	private static Map<String, String> convert = Collections.synchronizedMap(new HashMap());

	protected PropertyUtil(String sourceUrl) {
		this.sourceUrl = sourceUrl;
		load();
	}

	public static PropertyUtil getInstance(String sourceUrl) {
		synchronized (lock) {
			PropertyUtil manager = (PropertyUtil) instance.get(sourceUrl);
			if (manager == null) {
				manager = new PropertyUtil(sourceUrl);
				instance.put(sourceUrl, manager);
			}
			return manager;
		}
	}

	private synchronized void load() {
		try {
			resourceBundle = ResourceBundle.getBundle(sourceUrl);
		} catch (Exception e) {
			throw new RuntimeException("sourceUrl = " + sourceUrl
					+ "file load error!", e);
		}
	}

	public synchronized String getProperty(String key) {
		return resourceBundle.getString(key);
	}

	public Map<String, String> readyConvert() {
		Enumeration<?> enu = resourceBundle.getKeys();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = resourceBundle.getString(key);
			convert.put(value, key);
		}
		return convert;
	}

	public Map<String, String> readyConvert(ResourceBundle resourcebundle) {
		Enumeration<?> enu = resourcebundle.getKeys();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = resourcebundle.getString(key);
			convert.put(value, key);
		}
		return convert;
	}
}