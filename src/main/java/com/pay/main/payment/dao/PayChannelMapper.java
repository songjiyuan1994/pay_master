package com.pay.main.payment.dao;

import java.util.List;
import java.util.Map;

import com.pay.main.payment.entity.PayChannel;
import org.apache.ibatis.annotations.Param;

public interface PayChannelMapper {
    public int insertSelective(Map<String, Object> param);

    public int updateByPrimaryKeySelective(Map<String, Object> param);

    // 根据平台唯一订单号查询订单信息
    public PayChannel selectByPrimaryKey(String tradeNo);

    // 根据商户ID和商户订单号退款
    public PayChannel selectByPrimaryKeyMer(String tradeNo, String merNo);

    public int updateByPrimaryKey(PayChannel record);

    int updateNotifyState(@Param("tradeNo") String tradeNo);

    List<PayChannel> selectItem(@Param("merId") String merId
            , @Param("startTime") String startTime
            , @Param("endTime") String endTime,@Param("payType") String payType);

    List<PayChannel> selectOne(@Param("merId")String ulMerId, @Param("merNo")String merNo
            , @Param("platformNo")String platformNo, @Param("payNo") String payNo);

    List<PayChannel> getReCallback(@Param("merId")String ulMerId);

    PayChannel getInquiry(@Param("outTradeNo")String out_trade_no);
}