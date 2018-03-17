package com.pay.main.payment.core;

import com.pay.main.payment.config.SwiftpassConfig;
import com.pay.main.payment.config.ZYFConfig;
import com.pay.main.payment.entity.PayChannel;
import com.pay.main.payment.service.ISwitchMerchantService;
import com.pay.main.payment.service.ITransChannelService;
import com.pay.main.payment.util.DataProcessUtil;
import com.pay.main.payment.util.HttpUtil;
import com.pay.main.payment.util.MD5;
import com.pay.main.payment.util.ReturnUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.*;

/**
 * Created by songjiyuan on 2017/11/7.
 */

@Service("zyfCore")
public class ZYFPayCore {
    private static Logger logger = LoggerFactory.getLogger(ZYFPayCore.class);
    @Autowired
    PayCore payCore;
    @Autowired
    ITransChannelService transChannelService;
    @Autowired
    ISwitchMerchantService switchMerchantService;

    /**
     * 下单
     *
     * @param dataMap
     * @return
     */
    public Map<String, Object> placeOrder(Map<String, String> dataMap) {
        logger.info("支付下单-初始化—掌宜付");
        dataMap.put("ip", null != dataMap.get("ip") ? dataMap.get("ip") : "127.0.0.1");
        dataMap.put("channel", "QY"); // 支付渠道入库用
        // 2.1 数据入库
        boolean bool = payCore.savePayOrder(dataMap, true);
        if (!bool) {
            return ReturnUtil.returnFail("下单参数有误！", 1005);
        }

        // 创建下单信息
        SortedMap<String, String> orderMap = new TreeMap<String, String>();
        orderMap.put("partner_id", ZYFConfig.merId);
        orderMap.put("app_id", ZYFConfig.AppId);
        orderMap.put("wap_type", getPayTypeMark(dataMap.get("service")));
        orderMap.put("money", dataMap.get("total_fee"));
        orderMap.put("out_trade_no", dataMap.get("orderNo"));
        orderMap.put("subject", "充值服务");
        orderMap.put("return_url", dataMap.get("callback_url"));

        // 生成下单签名
        Map<String, String> params = SwiftpassPayCore.paraFilter(orderMap);
        StringBuilder buf = new StringBuilder();
        SwiftpassPayCore.buildPayParams(buf, params, true);
        String preStr = buf.toString();
        String onSign = MD5.sign(preStr, "&key=" + ZYFConfig.Key, "utf-8").toUpperCase();
        orderMap.put("sign", onSign);
        StringBuilder sb = new StringBuilder();
        sb.append(ZYFConfig.req_url);
        sb.append("?");
        SwiftpassPayCore.buildPayParams(sb, orderMap, true);
        try {
            logger.error("支付下单-报文—掌宜付:", orderMap);
            // 拼接返回信息
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("code_url", sb.toString());
            resultMap.put("out_trade_no", dataMap.get("out_trade_no"));
            resultMap.put("total_fee", dataMap.get("total_fee"));
            return ReturnUtil.returnInfo(DataProcessUtil.removeNullMap(resultMap));
        } catch (Exception ex) {
            logger.error("支付下单-错误—掌宜付：", ex);
            return ReturnUtil.returnFail("下单参数有误！", 1005);
        }
    }

    public boolean getAutograph(Map<String, String> param) throws Exception {
        logger.info("回调信息-威富通：" + param);
        if (param != null) {
            if (param.containsKey("sign")) {
                boolean result = false;
                if (param.containsKey("sign")) {
                    String sign = param.get("sign");
                    param.remove("sign");
                    StringBuilder buf = new StringBuilder((param.size() + 1) * 10);
                    SwiftpassPayCore.buildPayParams(buf, param, false);
                    String preStr = buf.toString();
                    String signRecieve = MD5.sign(preStr, "&key=" + ZYFConfig.Key, "utf-8").toUpperCase();
                    result = sign.equalsIgnoreCase(signRecieve);
                }
                if (!result) {
                    logger.info("ZYF验证签名不通过");
                } else {
                    return setSuccPayInfo(param);
                }
            }
        }
        return false;
    }

    /**
     * 选择支付方式
     *
     * @param payType
     * @return
     */
    public String getPayTypeMark(String payType) {
        String index = "";
        if (null != payType) {
            if ("pay.weixin.wappay".equals(payType)) { // 微信WAP
                index = "1";
            } else if ("pay.alipay.native".equals(payType)) { // 支付宝扫码
                index = "2";
            }
        }
        return index;
    }

    public boolean setSuccPayInfo(Map<String, String> vo) {
        String orderNo = vo.get("out_trade_no");
        Float fee = Float.parseFloat(vo.get("money")) / 100;
        // 修改订单信息
        Date date = new Date();
        PayChannel payChannel = new PayChannel();
        payChannel.setTradeNo(orderNo); // 订单号(自己 )
        payChannel.setChannelNo(vo.get("invoice_no")); //
        payChannel.setWechatNo(vo.get("up_invoice_no")); //
//        payChannel.setpType(payType); // 支付方式
        payChannel.setpState(1); // 支付状态为成功
        payChannel.setpFee(fee); // 金额
        payChannel.setModifiedTime(date);
//        DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date pDate = new Date();
//        try {
//            pDate = fmt.parse(vo.get("payment_time"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        payChannel.setpTime(date);
        // 修改数据库值
        boolean bool = false;
        try {
            bool = transChannelService.updateByPrimaryKeySelective(payChannel);
        } catch (Exception ex) {
            logger.error("更新支付订单状态错误：", ex);
        }

        // 通知商户支付状态
        if (bool) {
            PayChannel info = transChannelService.getByPrimaryKey(orderNo);
            // 查询订单号，发送通知下游支付状态
            return payCore.sendNotify(info, "1");
        } else {
            logger.error("数据状态更新错误！");
        }
        return false;
    }
}
