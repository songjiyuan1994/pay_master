package com.pay.main.payment.core;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pay.main.payment.config.Constant;
import com.pay.main.payment.config.SwiftpassConfig;
import com.pay.main.payment.entity.PayChannel;
import com.pay.main.payment.entity.SwitchMerchant;
import com.pay.main.payment.entity.TransChannel;
import com.pay.main.payment.service.ISwitchMerchantService;
import com.pay.main.payment.service.ITransChannelService;
import com.pay.main.payment.thread.RunThread;
import com.pay.main.payment.util.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 威富通
 *
 * @author Guo
 */
@Service("spPayCore")
public class SwiftpassPayCore {
    Logger logger = LoggerFactory.getLogger(SwiftpassPayCore.class);

    @Autowired
    PayCore payCore;
    @Autowired
    ITransChannelService transChannelService;
    @Autowired
    ISwitchMerchantService switchMerchantService;

    /**
     * 1.1 威富通下单
     *
     * @return
     * @throws IOException
     */
    public Map<String, Object> placeOrder(Map<String, String> dataMap) {
        logger.info("支付下单--选用威富通订单接口！");
        dataMap.put("ip", null != dataMap.get("ip") ? dataMap.get("ip") : "127.0.0.1");
        dataMap.put("channel", "ZZ"); // 支付渠道入库用

        String INFO_MCHID = null; // 商户号
        String SIGN_KEY = null; // 签名KEY
        SwitchMerchant selectSM = null;
        // 2.1 数据入库
        boolean bool = payCore.savePayOrder(dataMap, true);
        if (!bool) {
            return ReturnUtil.returnFail("下单参数有误！", 1005);
        }

        // 创建下单信息
        SortedMap<String, String> orderMap = new TreeMap<String, String>();
//        orderMap.put("service", dataMap.get("service"));
//        orderMap.put("version", "2.0");
//        orderMap.put("total_fee", dataMap.get("total_fee"));
//        orderMap.put("charset", "UTF-8");
//        orderMap.put("sign_type", "MD5");
//        orderMap.put("mch_id", SwiftpassConfig.merId);
//        orderMap.put("out_trade_no", dataMap.get("orderNo"));
//        orderMap.put("body", dataMap.get("body"));
//        orderMap.put("attach", dataMap.get("attach"));
//        orderMap.put("total_fee", dataMap.get("total_fee"));
//        orderMap.put("mch_create_ip", dataMap.get("ip"));
//        orderMap.put("notify_url", SwiftpassConfig.notify_url);
//        orderMap.put("nonce_str", String.valueOf(new Date().getTime()));
//        orderMap.put("callback_url", dataMap.get("callback_url"));
//        orderMap.put("device_info", "iOS_WAP");
//        orderMap.put("mch_app_name", "AppStore");
//        orderMap.put("mch_app_id", "http://www.baidu.com");
//        orderMap.put("pay_way", dataMap.get("service"));
        orderMap.put("pay_way", getPayTypeMark(dataMap.get("service")));
        orderMap.put("total_fee", dataMap.get("total_fee"));
        orderMap.put("mch_id", SwiftpassConfig.merId);
        orderMap.put("out_trade_no", dataMap.get("orderNo"));
        orderMap.put("subject", dataMap.get("body"));
        orderMap.put("terminal_ip", dataMap.get("ip"));
        orderMap.put("notify_url", SwiftpassConfig.notify_url);
        orderMap.put("callback_url", dataMap.get("callback_url"));

        // 生成下单签名
        Map<String, String> params = SwiftpassPayCore.paraFilter(orderMap);
        StringBuilder buf = new StringBuilder();
        SwiftpassPayCore.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String onSign = MD5.sign(preStr, "&key=" + SwiftpassConfig.Key, "utf-8");
        orderMap.put("sign", onSign);

        try {
            if (dataMap.get("service").equals("pay.weixin.wappay")){
                StringBuilder sb = new StringBuilder();
                sb.append(SwiftpassConfig.req_url);
                sb.append("?");
                for (String key : orderMap.keySet()) {
                    String value = orderMap.get(key);
                    if (value == null || value.equals("")) {
                        continue;
                    }
                    sb.append(key);
                    sb.append("=");
                    sb.append(value);
                    sb.append("&");
                }
                sb.setLength(sb.length() - 1);
                // 拼接返回信息
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("out_trade_no", dataMap.get("out_trade_no"));
                resultMap.put("total_fee", dataMap.get("total_fee"));
                resultMap.put("code_url", sb.toString());
                return ReturnUtil.returnInfo(DataProcessUtil.removeNullMap(resultMap));
            }else {
                String httpPost2 = HttpUtil.doPost2(SwiftpassConfig.req_url, orderMap, "utf-8");
                JSONObject object = JSON.parseObject(httpPost2);
                logger.error("威富通下单返回信息", object.toString());
                // 拼接返回信息
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("out_trade_no", object.get("out_trade_no"));
                resultMap.put("code_url", object.get("qrcode"));
                resultMap.put("total_fee", object.get("total_fee"));
                resultMap.put("err_msg", object.get("err_msg"));
                resultMap.put("err_code", object.get("err_code"));
                return ReturnUtil.returnInfo(DataProcessUtil.removeNullMap(resultMap));
            }
        } catch (Exception ex) {
            logger.error("威富通下单错误：", ex);
            return ReturnUtil.returnFail("下单参数有误！", 1005);
        }
    }

    public boolean getAutograph(Map<String, String> param) throws Exception {
        logger.info("回调信息-威富通：" + param);
        System.err.println(param);
        if (param != null) {
//            if (param.containsKey("sign")) {
//                boolean result = false;
//                if (param.containsKey("sign")) {
//                    String sign = param.get("sign");
//                    param.remove("sign");
//                    StringBuilder buf = new StringBuilder((param.size() + 1) * 10);
//                    SwiftpassPayCore.buildPayParams(buf, param, false);
//                    String preStr = buf.toString();
//                    String signRecieve = MD5.sign(preStr, "&key=" + SwiftpassConfig.Key, "utf-8");
//                    result = sign.equalsIgnoreCase(signRecieve);
//                }
//                if (!result) {
//                    logger.info("SwiftpassPay验证签名不通过");
//                } else {
//                    String status = param.get("status");
//                    if (status != null && "0".equals(status)) {
//                        String result_code = param.get("result_code");
//                        if (result_code != null && "0".equals(result_code)) {
            // 此处可以在添加相关处理业务，校验通知参数中的商户订单号out_trade_no和金额total_fee是否和商户业务系统的单号和金额是否一致，一致后方可更新数据库表中的记录。
            return setSuccPayInfo(param);
//                        }
//                    }
//                }
//            }
        }
        return false;
    }

    //获取请求体中的字符串(POST)
    private static String getBodyData(HttpServletRequest request) {
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine()))
                data.append(line);
        } catch (IOException e) {
        } finally {
        }
        return data.toString();
    }

    /**
     * 修改数据库中值(支付成功)
     *
     * @param vo
     * @return
     */
    public boolean setSuccPayInfo(Map<String, String> vo) {
//        String paySign = vo.get("trade_type");
//        Integer payType = getPayTypeMarkNum(paySign);
//        Integer payType = 4;
        String orderNo = vo.get("out_trade_no");
        /* 扣量 */
        if (orderNo.endsWith("1")) {
            return true;
        }
        /* 扣量 */
        Float fee = Float.parseFloat(vo.get("total_fee")) / 100;
        // 修改订单信息
        Date date = new Date();
        PayChannel payChannel = new PayChannel();
        payChannel.setTradeNo(orderNo); // 订单号(自己 )
        payChannel.setChannelNo(vo.get("trade_no")); //
        payChannel.setWechatNo(vo.get("trade_no")); //
//        payChannel.setpType(payType); // 支付方式
        payChannel.setpState(1); // 支付状态为成功
        payChannel.setpFee(fee); // 金额
        payChannel.setModifiedTime(date);
        DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        Date pDate = new Date();
        try {
            pDate = fmt.parse(vo.get("payment_time"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        payChannel.setpTime(pDate);
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

    /**
     * 验证返回参数
     *
     * @param params
     * @param key
     * @return
     */
    public static boolean checkParam(Map<String, String> params, String key) {
        boolean result = false;
        if (params.containsKey("sign")) {
            String sign = params.get("sign");
            params.remove("sign");
            StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
            SwiftpassPayCore.buildPayParams(buf, params, false);
            String preStr = buf.toString();
            String signRecieve = MD5.sign(preStr, "&key=" + key, "utf-8");
            result = sign.equalsIgnoreCase(signRecieve);
        }
        return result;
    }

    /**
     * 过滤参数
     *
     * @param sArray
     * @return
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>(sArray.size());
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    public static String payParamsToString(Map<String, String> payParams) {
        return payParamsToString(payParams, false);
    }

    public static String payParamsToString(Map<String, String> payParams, boolean encoding) {
        return payParamsToString(new StringBuilder(), payParams, encoding);
    }

    public static String payParamsToString(StringBuilder sb, Map<String, String> payParams, boolean encoding) {
        buildPayParams(sb, payParams, encoding);
        return sb.toString();
    }

    public static void buildPayParams(StringBuilder sb, Map<String, String> payParams, boolean encoding) {
        List<String> keys = new ArrayList<String>(payParams.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            sb.append(key).append("=");
            if (encoding) {
                sb.append(urlEncode(payParams.get(key)));
            } else {
                sb.append(payParams.get(key));
            }
            sb.append("&");
        }
        sb.setLength(sb.length() - 1);
    }

    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Throwable e) {
            return str;
        }
    }

    public static Element readerXml(String body, String encode) throws DocumentException {
        SAXReader reader = new SAXReader(false);
        InputSource source = new InputSource(new StringReader(body));
        source.setEncoding(encode);
        Document doc = reader.read(source);
        Element element = doc.getRootElement();
        return element;
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
                index = "WX";
            } else if ("pay.alipay.native".equals(payType)) { // 支付宝扫码
                index = "ALI_SCAN";
            }
        }
        return index;
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
            if ("unified.trade.pay".equals(payType)) { // 统一APP
                index = Constant.PAYTYPE_WA;
            } else if ("pay.weixin.native".equals(payType)) { // 微信扫码
                index = Constant.PAYTYPE_WS;
            } else if ("pay.weixin.jspay".equals(payType)) { // 微信公众号
                index = Constant.PAYTYPE_WP;
            } else if ("pay.weixin.wappay".equals(payType)) { // 微信WAP
                index = Constant.PAYTYPE_WW;
            } else if ("pay.alipay.native".equals(payType)) { // 支付宝扫码
                index = Constant.PAYTYPE_AS;
            } else if ("pay.tenpay.native".endsWith(payType)) { // qq钱包扫码
                index = Constant.PAYTYPE_QS;
            } else if ("pay.tenpay.jspay".endsWith(payType)) { // qq钱包公众号
                index = Constant.PAYTYPE_QP;
            }
        }
        return index;
    }
}