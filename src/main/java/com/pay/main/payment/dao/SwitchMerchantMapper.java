package com.pay.main.payment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.main.payment.entity.SwitchMerchant;

public interface SwitchMerchantMapper {
	public List<SwitchMerchant> selectAllInfo();

	public SwitchMerchant selectByPrimaryKey(String appid);
	
	public List<SwitchMerchant> selectAutoList(String id);
	
	public Integer updataTotal(Map<String,Object> param);
	
	public SwitchMerchant selectAutoInfo(@Param("appid")String appid, @Param("mchid")String mchid);

}