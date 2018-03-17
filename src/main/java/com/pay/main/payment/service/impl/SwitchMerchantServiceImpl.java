package com.pay.main.payment.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.main.payment.dao.SwitchMerchantMapper;
import com.pay.main.payment.entity.SwitchMerchant;
import com.pay.main.payment.service.ISwitchMerchantService;
import com.pay.main.payment.util.DataProcessUtil;

/**
 * 上游接口信息
 * 
 * @author Guo
 */
@Service("switchMerchantService")
public class SwitchMerchantServiceImpl implements ISwitchMerchantService {

	@Autowired
	SwitchMerchantMapper switchMerchantMapper;

	@Override
	public List<SwitchMerchant> getAllInfo() {
		List<SwitchMerchant> selectAllInfo = switchMerchantMapper.selectAllInfo();
		return selectAllInfo;
	}

	@Override
	public SwitchMerchant getByPrimaryKey(String id) {
		SwitchMerchant selectByPrimaryKey = switchMerchantMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	@Override
	public List<SwitchMerchant> getAutoList(String id) {
		List<SwitchMerchant> list = switchMerchantMapper.selectAutoList(id);
		return list;
	}

	@Override
	public boolean setTotal(SwitchMerchant sm) {
		Map<String, Object> param = DataProcessUtil.convertBeanToMap(sm);
		int index = switchMerchantMapper.updataTotal(param);
		return index > 0;
	}

	@Override
	public SwitchMerchant getAutoInfo(String id, String mchid) {
		SwitchMerchant selectByPrimaryKey = switchMerchantMapper.selectAutoInfo(id, mchid);
		return selectByPrimaryKey;
	}
}