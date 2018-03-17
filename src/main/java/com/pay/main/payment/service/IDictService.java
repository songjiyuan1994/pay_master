package com.pay.main.payment.service;

import com.pay.main.payment.entity.Dict;

public interface IDictService {
	public Dict getDictInfo(String type, String key);
}