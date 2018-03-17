package com.pay.main.payment.web;

import com.pay.main.payment.dao.MybatisPaging;
import com.pay.main.payment.entity.PaySummaryEntity;
import com.pay.main.payment.entity.UserLogin;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("summary")
public class PaySummaryController extends MybatisPaging {
    Log logger = LogFactory.getLog(PaySummaryController.class);
    @Autowired
    IPaySummaryService paySummaryService;

    /**
     * 获取数据汇总
     *
     * @param startTime
     * @param endTime
     * @param request
     * @return
     */
    @RequestMapping(value = "/total", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> total(String startTime, String endTime, HttpServletRequest request) {
        String startDate = startTime + " 00:00:00";
        String endDate = endTime + " 23:59:59";
        UserLogin session = (UserLogin) request.getSession().getAttribute(LoginUtils.LOGIN_USERINFO);
        if (session == null) {
            return buildErrorResult("登录超时请重新登录！");
        }
        try {
            List<PaySummaryEntity> rtnList = paySummaryService.getSummaryTotal(session.getUlMerId(), startDate, endDate);
            Map<String, Object> result = new HashMap<>();
            result.put("state", true);
            result.put("data", rtnList);
            float totalMoney = 0f;
            float settlement = 0f;
            int successCount = 0;
            for (PaySummaryEntity item:rtnList) {
                totalMoney += item.getTotalMoney();
                settlement += item.getSettlement();
                successCount += item.getSuccessCount();
            }
            result.put("totalMoney", totalMoney);
            result.put("settlement", settlement);
            result.put("successCount", successCount);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return buildErrorResult("服务器出现错误！");
        }
    }
}