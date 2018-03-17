package com.pay.main.payment.service;

import com.pay.main.payment.entity.PayChannel;
import com.pay.main.payment.entity.PaySummaryEntity;

import java.util.List;

/**
 * 渠道订单接口
 */
public interface IPayChannelService {
	public int insertSelective(PayChannel record);

	// 根据平台唯一订单号查询订单信息
	PayChannel getByPrimaryKey(String tradeNo);
	
	// 根据商户ID和商户订单号退款
	PayChannel getByPrimaryKeyMer(String merId,String merNo);

	int updateByPrimaryKeySelective(PayChannel record);

	int updateByPrimaryKey(PayChannel record);

    boolean updateNotifyState(String tradeNo);

	List<PayChannel> getSelectItem(String ulMerId, String startDate, String endDate,String payType);

	List<PayChannel> getSelectOne(String ulMerId, String merNo, String platformNo, String payNo);

    List<PayChannel> getReCallback(String ulMerId);

	PayChannel getInquiry(String out_trade_no);
}