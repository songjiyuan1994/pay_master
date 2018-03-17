package com.pay.main.payment.web;

import com.pay.main.payment.core.PayCore;
import com.pay.main.payment.core.SwiftpassPayCore;
import com.pay.main.payment.core.ZYFPayCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 威富通支付
 *
 * @author Guo
 */
@Controller
@RequestMapping("pay")
public class SwiftpassPayController {
	Logger logger = LoggerFactory.getLogger(SwiftpassPayController.class);

	@Autowired
    PayCore payCore;
	@Autowired
	SwiftpassPayCore spPayCore;
	@Autowired
	ZYFPayCore zyfCore;

	@RequestMapping(value = "gateway", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> gateway(HttpServletRequest request, HttpServletResponse resp) {
		logger.info("支付下单-威富通!");
		String keys = "service,mch_id,out_trade_no,body,attach,total_fee,notify_url,callback_url,sign";
		Map<String, Object> params = payCore.getRequestParameter(request, keys);
		if ("1000".equals(params.get("rtnCode"))) {
			@SuppressWarnings("unchecked")
			Map<String, String> dataMap = (Map<String, String>) params.get("rtnMsg");
			return spPayCore.placeOrder(dataMap);
		}
		return params;
	}

	@RequestMapping(value = "order", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> order(HttpServletRequest request, HttpServletResponse resp) {
		logger.info("支付下单-威富通!");
		String keys = "service,mch_id,out_trade_no,body,attach,total_fee,notify_url,callback_url,sign";
		Map<String, Object> params = payCore.getRequestParameter(request, keys);
		if ("1000".equals(params.get("rtnCode"))) {
			@SuppressWarnings("unchecked")
			Map<String, String> dataMap = (Map<String, String>) params.get("rtnMsg");
			return zyfCore.placeOrder(dataMap);
		}
		return params;
	}

	@RequestMapping(value = "inquiry", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> inquiry(HttpServletRequest request, HttpServletResponse resp) {
		logger.info("支付下单-威富通!");
		String keys = "mch_id,out_trade_no,sign";
		Map<String, Object> params = payCore.getInquiry(request, keys);
		return params;
	}

	/**
	 * 回调信息接口(接受上游返回回调信息)
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/callback", method = RequestMethod.POST)
	public @ResponseBody String notifyUrl(HttpServletRequest request) {
		logger.info("接受回调信息-威富通!");
		try {
			request.setCharacterEncoding("utf-8");
//			String resString = XmlUtils.parseRequst(request);
//			System.err.println(resString);
			StringBuffer sb = new StringBuffer();
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			String wholeStr = sb.toString();
			logger.info("回调信息：" + wholeStr);
			Map<String, String> params = new HashMap<String, String>();
			String[] sp = wholeStr.split("&");
			String[] temp = null;
			for (String s : sp) {
				temp = s.split("=");
				params.put(temp[0], temp[1]);
			}
			boolean autograph = spPayCore.getAutograph(params);
			if (autograph) {
				return "success";
			}else {
				return "fail";
			}
		} catch (Exception ex) {
			logger.error("回调信息错误-威富通：", ex);
		}
		return "fail";
	}

	/**
	 * 回调信息接口(接受上游返回回调信息)
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/notify", method = RequestMethod.GET)
	public @ResponseBody String notify(HttpServletRequest request) {
		logger.info("接受回调信息-zyf!");
		try {
			request.setCharacterEncoding("utf-8");
			logger.info("接受回调信息-zyf:");
			Map<String, String> params = new HashMap<String, String>();
			Enumeration<?> enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String paraName = (String) enu.nextElement();
				params.put(paraName, request.getParameter(paraName));
			}
			logger.info("回调信息-zyf：" + params);
			boolean autograph = zyfCore.getAutograph(params);
			if (autograph) {
				return "0";
			}else {
				return "1";
			}
		} catch (Exception ex) {
			logger.error("回调信息错误-威富通：", ex);
		}
		return "1";
	}
}