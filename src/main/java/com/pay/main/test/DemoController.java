package com.pay.main.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.main.payment.util.DataProcessUtil;
import com.pay.main.payment.util.EncryptionUtil;

@Controller
@RequestMapping("testpay")
public class DemoController {
	Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	/**
	 * 测试打印回调信息接口
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/demoHD", method = RequestMethod.POST)
	public @ResponseBody String test(HttpServletRequest request) throws IOException {
		// 获取请求体数据
		BufferedReader br = request.getReader();
		String str, wholeStr = "";
		while ((str = br.readLine()) != null) {
			wholeStr += str;
		}
		
		String sign = null;
		
		// 将数据转换成MAP
		Map<String, Object> params = new HashMap<String, Object>();
		str = URLDecoder.decode(wholeStr, "UTF-8");
		String[] values = str.split("&");
		for (int i = 0; i < values.length; i++) {
			String value = values[i];
			String[] maps = value.split("=");
			params.put(maps[0], maps[1]);
		}
		sign = params.get("sign") + "";
		params.remove("sign");
		String textLink = DataProcessUtil.textLink(params, false);
		
		System.err.println(sign);
		System.err.println(wholeStr);
		return "SUCCESS";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/print", method ={RequestMethod.POST,RequestMethod.GET})
	public void wechatDemo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取请求体数据
		BufferedReader br = request.getReader();
		String str, wholeStr = "";
		while ((str = br.readLine()) != null) {
			wholeStr += str;
		}

		// 获取get请求所有参数
		Enumeration<?> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			wholeStr += paraName + "=" + request.getParameter(paraName);
		}
		
		logger.info(wholeStr);
		
		PrintWriter out = response.getWriter();
		out.print(wholeStr);
		out.close();
		out.flush();
	}
	
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("prince", "0.02");
		params.put("state", "1");
		params.put("type", "1");
		params.put("merNo", "YLWLLL20170612151305");

		String textLink = DataProcessUtil.textLink(params, true); // 拼接字符串
		String sign = EncryptionUtil.md5s(textLink + "&&&" + "DqubTWbUier1sTBhASDvFeDU7hR0lwRg");
		System.err.println(sign);
	}
}