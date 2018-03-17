package com.pay.main.payment.web;

import com.pay.main.payment.dao.MybatisPaging;
import com.pay.main.payment.entity.PayChannel;
import com.pay.main.payment.entity.PaySummaryEntity;
import com.pay.main.payment.entity.UserLogin;
import com.pay.main.payment.service.IPayChannelService;
import com.pay.main.payment.service.IPaySummaryService;
import com.pay.main.payment.util.LoginUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("select")
public class PayItemController extends MybatisPaging {
    Log logger = LogFactory.getLog(PayItemController.class);
    @Autowired
    IPayChannelService payChannelService;

    /**
     * 获取数据汇总
     *
     * @param startTime
     * @param endTime
     * @param request
     * @return
     */
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> total(String startTime, String endTime,String payType, HttpServletRequest request) {
        String startDate = startTime + " 00:00:00";
        String endDate = endTime + " 23:59:59";
        UserLogin session = (UserLogin) request.getSession().getAttribute(LoginUtils.LOGIN_USERINFO);
        if (session == null) {
            return buildErrorResult("登录超时请重新登录！");
        }
        try {
            List<PayChannel> rtnList = payChannelService.getSelectItem(session.getUlMerId(), startDate, endDate,payType);
            Map<String, Object> result = new HashMap<>();
            result.put("state", true);
            result.put("data", rtnList);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return buildErrorResult("服务器出现错误！");
        }
    }
    @RequestMapping(value = "/one", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> oneOrder(String merNo, String platformNo,String payNo, HttpServletRequest request) {
        UserLogin session = (UserLogin) request.getSession().getAttribute(LoginUtils.LOGIN_USERINFO);
        if (session == null) {
            return buildErrorResult("登录超时请重新登录！");
        }
        try {
            List<PayChannel> rtnList = payChannelService.getSelectOne(session.getUlMerId(), merNo, platformNo,payNo);
            Map<String, Object> result = new HashMap<>();
            result.put("state", true);
            result.put("data", rtnList);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return buildErrorResult("服务器出现错误！");
        }
    }
    @RequestMapping(value = "/reCallback", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> reCallback(HttpServletRequest request) {
        UserLogin session = (UserLogin) request.getSession().getAttribute(LoginUtils.LOGIN_USERINFO);
        if (session == null) {
            return buildErrorResult("登录超时请重新登录！");
        }
        try {
            List<PayChannel> rtnList = payChannelService.getReCallback(session.getUlMerId());
            Map<String, Object> result = new HashMap<>();
            result.put("state", true);
            result.put("data", rtnList);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return buildErrorResult("服务器出现错误！");
        }
    }


    public static String getpayType(int payType) {
        switch (payType) {
            case 1:
                return "微信APP";
            case 2:
                return "微信扫码";
            case 3:
                return "微信公众号";
            case 4:
                return "微信WAP";
            case 5:
                return "其他";
            case 6:
                return "支付宝APP";
            case 7:
                return "支付宝扫码";
            case 8:
                return "支付宝WAP";
            default:
                return "其他";
        }
    }
    public static String getPayState(int stete) {
        switch (stete) {
            case 0:
                return "未支付";
            case 1:
                return "支付成功";
            case 2:
                return "退款";
            default:
                return "其他";
        }
    }
}