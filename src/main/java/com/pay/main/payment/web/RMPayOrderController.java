package com.pay.main.payment.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.main.payment.core.PayCore;
import com.pay.main.payment.util.CommonUtil;
import com.pay.main.payment.util.ReturnUtil;

/**
 * 融脉商户接口
 * 
 * @author Guo
 */
@Controller
@RequestMapping("rmpay")
public class RMPayOrderController {
//	Logger logger = LoggerFactory.getLogger(RMPayOrderController.class);
//
//	@Autowired
//	PayCore payCore;
//
//	/**
//	 * 商户获取下单信息接口
//	 *
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
//	public @ResponseBody Map<String, Object> placeOrder(HttpServletRequest request) {
//		logger.info("支付下单-融脉");
//		String keys = "payType,merId,merNo,code,title,describe,attach,price,notifyUrl,openId,synUrl,sign";
//		Map<String, Object> info = payCore.getRequestParameter(request, keys);
//		return null;
//	}
//
//	/**
//	 * 回调信息接口(接受上游返回回调信息)
//	 *
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/notifyUrl", method = RequestMethod.POST)
//	public @ResponseBody String notifyUrl(HttpServletRequest request) {
//		String str, wholeStr = "";
//		logger.info("接受回调信息-融脉!");
//		try {
//			// 获取请求体数据,转换成实体
//			BufferedReader br = request.getReader();
//			while ((str = br.readLine()) != null) {
//				wholeStr += str;
//			}
//			logger.info("回调信息-融脉：" + wholeStr);
//
//			// 数据加密签名
//			if (bool)
//				return "success";
//		} catch (Exception ex) {
//			logger.error("回调信息接口错误-融脉：", ex);
//		}
//		return "fail";
//	}
//
//	/**
//	 * 退款接口
//	 *
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "/refund", method = RequestMethod.POST)
//	private @ResponseBody Map<String, Object> refund(HttpServletRequest request) {
//		logger.info("退款接口-融脉！");
//		// 获取商户号
//		String merId = request.getParameter("merId"); // 商户
//		String merNo = request.getParameter("merNo"); // 商户订单号
//
//		String ip = CommonUtil.getIp(request);
//		logger.info("ip:" + ip);
//
//		if ("139.129.241.116".equals(ip)) {
//		} else {
//			return ReturnUtil.returnFail("非法入侵");
//		}
//	}
}