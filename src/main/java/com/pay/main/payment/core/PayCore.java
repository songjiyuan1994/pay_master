package com.pay.main.payment.core;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pay.main.payment.config.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.main.payment.entity.PayChannel;
import com.pay.main.payment.entity.SwitchMerchant;
import com.pay.main.payment.entity.UserLogin;
import com.pay.main.payment.service.IPayChannelService;
import com.pay.main.payment.service.ISwitchMerchantService;
import com.pay.main.payment.service.IUserLoginService;
import com.pay.main.payment.util.CommonUtil;
import com.pay.main.payment.util.DataFormatUtil;
import com.pay.main.payment.util.DataProcessUtil;
import com.pay.main.payment.util.EncryptionUtil;
import com.pay.main.payment.util.FileIOUtils;
import com.pay.main.payment.util.HttpUtil;
import com.pay.main.payment.util.ReturnUtil;
import com.pay.main.payment.vo.RMSendNotifyVO;

/**
 * 支付通用方法
 *
 * @author Guo
 */
@Service("payCore")
public class PayCore {
    private static Logger logger = LoggerFactory.getLogger(PayCore.class);

    @Autowired
    IPayChannelService payChannelService;
    @Autowired
    IUserLoginService userLoginService;
    @Autowired
    ISwitchMerchantService switchMerchantService;

    /**
     * 获取客户端下单参数（必传merId,商户sign）
     *
     * @param keyStr 获取参数名，逗号分割
     * @return
     */
    public Map<String, Object> getRequestParameter(HttpServletRequest request, String keyStr) {
        Map<String, Object> params = new HashMap<String, Object>();

        String[] key = null != keyStr ? keyStr.split(",") : null;

        // 遍历保存传参数据
        for (int i = 0; i < key.length; i++) {
            String onKey = key[i];
            params.put(onKey, request.getParameter(onKey));
        }

        // 打印传值
        logger.info("客户端下单传值:" + params.toString());

        // 判断用户是否开启支付
        Integer ulPaystate = 0;
        UserLogin userInfo = userLoginService.getByPrimaryKey(params.get("mch_id").toString());
        ulPaystate = null != userInfo ? userInfo.getUlPaystate() : 0;
        if (ulPaystate != 1) {
            return ReturnUtil.returnFail("非法商户号！", 1001);
        }

        // 判断签名正确性
        Map<String, Object> map = DataProcessUtil.removeNullMap(params);
        map.remove("sign");
        String textLink = DataProcessUtil.textLink(map, false); // 拼接字符串
        String md5s = EncryptionUtil.md5s(textLink + "&" + userInfo.getUlSign());
        String sign = params.get("sign") + "";
        if (!md5s.equals(sign)) {
            String signStr = textLink + "&" + userInfo.getUlSign();
            logger.info("生成签名字符串: " + signStr + " ==> " + md5s + " : " + sign);
            return ReturnUtil.returnFail("签名错误！", 1004);
        }

        // 生成订单号
        String orderNo = CommonUtil.getOrderNo();
        params.put("orderNo", orderNo);
        if (null == orderNo) {
            return ReturnUtil.returnFail("未知错误！", 1099);
        }

        return ReturnUtil.returnInfo(params);
    }

    /**
     * 动态获取下单详情
     *
     * @return
     */
    public Map<String, Object> getAutoSM(String appid) {
        Map<String, Object> map = new HashMap<String, Object>();

        // 处理自动下单和指定下单
        String mchid = null;
        boolean boole = false;
        String INFO_MCHID = null;// 商户号
        String SIGN_KEY = null;// 签名KEY
        float TOTAL = 0;
        // 数据库中查询appid对应的签名
        SwitchMerchant sm = null;
        sm = switchMerchantService.getByPrimaryKey(appid);
        if (null != sm) {
            INFO_MCHID = null != sm.getMchid() ? sm.getMchid() : INFO_MCHID;
            SIGN_KEY = null != sm.getSign() ? sm.getSign() : SIGN_KEY;
            TOTAL = null != sm.getTotal() ? sm.getTotal() : 0;
        } else {
            logger.error("回调信息数据校验CODE未查询到（" + appid + "），无法使用，请修改CODE：", null != sm ? sm.toString() : "SwitchMerchant 对象为null");
        }

        map.put("INFO_MCHID", INFO_MCHID);
        map.put("SIGN_KEY", SIGN_KEY);
        map.put("OBJECT_SM", sm);
        return map;
    }

    /**
     * 数据库入库2.0
     *
     * @return
     */
    public boolean savePayOrder(Map<String, String> params, boolean bool) {
        String merId = params.get("mch_id"); // 商户号
        String merDescribe = params.get("body"); // 商户描述
        String tradeNo = params.get("orderNo"); // 订单号
        String merchantNo = params.get("out_trade_no"); // 商户订单号
        String price = params.get("total_fee"); // 支付金额
        String pTitle = params.get("body"); // 标题名称
        String pAttach = params.get("attach"); // 自定义参数
        int pType = getPayTypeMarkNum(params.get("service")); // 支付类型
        Date pTime = null; // 支付成功时间null
        int pState = 0; // 支付状态默认0
        Date createTime = new Date(); // 创建时间
        String notifyUrl = params.get("notify_url"); // 商户通知地址
        // 分转元
        if (bool) {
            Float yuan = Float.parseFloat(price) / 100;
            price = yuan + "";
        }
        PayChannel payEntity = null;
        try {
            // 数据入库
            payEntity = new PayChannel(merId, merDescribe, tradeNo, merchantNo, "swiftpass", price, pTitle, pAttach, pType, pTime, pState, createTime, null, notifyUrl);
            payChannelService.insertSelective(payEntity);
        } catch (Exception ex) {
            logger.error("支付数据入库错误-数据：" + payEntity.toString() + "-日志:", ex);
            return false;
        }
        return true;
    }

    /**
     * 发送异步数据给商户
     *
     * @param payChannel
     * @return
     */
    public boolean sendNotify(PayChannel payChannel, String state) {
        boolean bool = false;
        if (null == payChannel)
            return bool;
        try {
            String merNo = payChannel.getMerchantNo();
            String title = payChannel.getpTitle();
            String attach = payChannel.getpAttach();
            String prince = payChannel.getpFee() + "";
            String type = payChannel.getpType() + "";
            String appId = payChannel.getAppId();
            String notifyUrl = payChannel.getNotifyUrl();
            String merId = payChannel.getMerId();
            String payNo = payChannel.getWechatNo();
            String platformNo = payChannel.getTradeNo();
            UserLogin userInfo = userLoginService.getByPrimaryKey(merId);
            RMSendNotifyVO vo = new RMSendNotifyVO(merId, merNo, attach, title, state, prince, platformNo, payNo, type, null);
            Map<String, Object> params = DataProcessUtil.convertBeanNotNullToMap(vo);
            params.remove("sign"); // 删除sign
            logger.info("回调信息需要加密数据：" + params);
            String textLink = DataProcessUtil.textLink(params, false); // 拼接字符串
            String sign = EncryptionUtil.md5s(textLink + "&" + userInfo.getUlSign());
            params.put("sign", sign); // 添加生成的sign
            String doPost = HttpUtil.doPost(notifyUrl, params, "UTF-8");
            logger.info("（" + merNo + "）通知商户返回信息：" + doPost);
            String temp = (doPost + "").toUpperCase().trim();
            if ("SUCCESS".equals(temp.toUpperCase())) {
                payChannelService.updateNotifyState(payChannel.getTradeNo());
                bool = true;
            } else {
                bool = false;
            }
        } catch (Exception ex) {
            logger.error("通知商户发送异步数据-sendNotify：", ex);
        }
        return bool;
    }


    /**
     * 获取支付类型
     *
     * @param payType
     * @return
     */
    public Integer getPayTypeMarkNum(String payType) {
        Integer index = 0;
        if (null != payType) {
            if ("pay.weixin.wappay".equals(payType)) { // 微信WAP
                index = Constant.PAYTYPE_WW;
            } else if ("pay.alipay.native".equals(payType)) { // 支付宝扫码
                index = Constant.PAYTYPE_AS;
            } else if ("pay.weixin.h5pay".equals(payType)) {
                index = Constant.PAYTYPE_WW;
            }
        }
        return index;
    }

    public Map<String, Object> getInquiry(HttpServletRequest request, String keys) {
        Map<String, Object> params = new HashMap<String, Object>();
        String[] key = null != keys ? keys.split(",") : null;
        // 遍历保存传参数据
        for (int i = 0; i < key.length; i++) {
            String onKey = key[i];
            params.put(onKey, request.getParameter(onKey));
        }
        // 打印传值
        logger.info("查询接口传值:" + params.toString());
        UserLogin userInfo = userLoginService.getByPrimaryKey(params.get("mch_id").toString());
        Integer ulPaystate = 0;
        ulPaystate = null != userInfo ? userInfo.getUlPaystate() : 0;
        if (ulPaystate != 1) {
            return ReturnUtil.returnFail("非法商户号！", 1001);
        }

        // 判断签名正确性
        Map<String, Object> map = DataProcessUtil.removeNullMap(params);
        map.remove("sign");
        String textLink = DataProcessUtil.textLink(map, false); // 拼接字符串
        String md5s = EncryptionUtil.md5s(textLink + "&" + userInfo.getUlSign());
        String sign = params.get("sign") + "";
        if (!md5s.equals(sign)) {
            String signStr = textLink + "&" + userInfo.getUlSign();
            logger.info("生成签名字符串: " + signStr + " ==> " + md5s + " : " + sign);
            return ReturnUtil.returnFail("签名错误！", 1004);
        }
        try {
            PayChannel payChannel = payChannelService.getInquiry(params.get("out_trade_no").toString());
            if (payChannel == null) {
                return ReturnUtil.returnFail("未查询到该订单！", 1011);
            } else {
                String merNo = payChannel.getMerchantNo();
                String title = payChannel.getpTitle();
                String attach = payChannel.getpAttach();
                String prince = payChannel.getpFee() + "";
                String type = payChannel.getpType() + "";
                String merId = payChannel.getMerId();
                String payNo = payChannel.getWechatNo();
                String state = payChannel.getpState().toString();
                String platformNo = payChannel.getTradeNo();
                RMSendNotifyVO vo = new RMSendNotifyVO(merId, merNo, attach, title, state, prince, platformNo, payNo, type, null);
                Map<String, Object> rtnData = DataProcessUtil.convertBeanNotNullToMap(vo);
                rtnData.remove("sign"); // 删除sign
                logger.info("查询信息需要加密数据：" + params);
                String DataTextLink = DataProcessUtil.textLink(rtnData, false); // 拼接字符串
                String DataSign = EncryptionUtil.md5s(DataTextLink + "&" + userInfo.getUlSign());
                rtnData.put("sign", DataSign); // 添加生成的sign
                return rtnData;
            }
        } catch (Exception ex) {
            return ReturnUtil.returnFail("未知错误！", 1099);
        }
    }
}