package com.pay.main.payment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.main.payment.dao.DictMapper;
import com.pay.main.payment.entity.Dict;
import com.pay.main.payment.service.IDictService;

@Service("dictService")
public class DictserviceImpl implements IDictService {
	Logger logger = LoggerFactory.getLogger(DictserviceImpl.class);

	@Autowired
	DictMapper dictMapper;

	@Override
	public Dict getDictInfo(String type, String key) {
		Dict dict = null;
		try {
			dict = dictMapper.selectByPrimaryKey(type, key);
		} catch (Exception ex) {
			logger.error("查询字典错误（tyep:" + type + ",key:" + key + "）:", ex);
		}
		return dict;
	}
}