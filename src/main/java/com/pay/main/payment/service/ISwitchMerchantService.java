package com.pay.main.payment.service;

import java.util.List;

import com.pay.main.payment.entity.SwitchMerchant;

/**
 * 上游接口信息
 * 
 * @author Guo
 */
public interface ISwitchMerchantService {
	public List<SwitchMerchant> getAllInfo();

	public SwitchMerchant getByPrimaryKey(String id);

	public List<SwitchMerchant> getAutoList(String id);

	public boolean setTotal(SwitchMerchant sm);

	public SwitchMerchant getAutoInfo(String id, String mchid);
}