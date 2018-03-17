package com.pay.main.payment.service;

import com.pay.main.payment.entity.PayChannel;
import com.pay.main.payment.entity.TransChannel;

/**
 * 渠道订单接口
 */
public interface ITransChannelService {
	
	public boolean insertSelective(PayChannel record) throws Exception;

	boolean updateByPrimaryKeySelective(PayChannel record) throws Exception;

	// 根据平台唯一订单号查询订单信息
	PayChannel getByPrimaryKey(String tradeNo);

	// 根据商户ID和商户订单号退款
	PayChannel getByPrimaryKeyMer(String merId, String merNo);

}