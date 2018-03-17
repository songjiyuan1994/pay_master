package com.pay.main.payment.web;

import com.pay.main.payment.dao.MybatisPaging;
import com.pay.main.payment.entity.UserLogin;
import com.pay.main.payment.service.IUserLoginService;
import com.pay.main.payment.util.CaptchaUtils;
import com.pay.main.payment.util.LoginUtils;
import com.pay.main.payment.util.ReturnUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("login")
public class LoginController extends MybatisPaging{
	private static Log logger = LogFactory.getLog(LoginController.class);
	@Autowired
	IUserLoginService userLoginService;
	@RequestMapping(value = "onLogin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	Map<String, Object> onLogin(@RequestParam String username, String password, String captcha, HttpServletRequest request) {
		return userLoginService.getLoginAccount(request, username, password, captcha);
	}
	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "onCaptcha", method = RequestMethod.GET)
	public void onCaptcha(HttpServletRequest request, HttpServletResponse response) {
		try {
			BufferedImage image = CaptchaUtils.getCaptcha(request, response);
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (ServletException | IOException ex) {
			logger.error("获取验证码：", ex);
		}
	}

	@RequestMapping(value = "outLogin", method = RequestMethod.GET)
	public @ResponseBody void outLogin(HttpServletRequest request, HttpServletResponse response) {
		boolean bool = userLoginService.getOutLogin(request, response);
		if(bool){
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				String contextPath = request.getContextPath();// 获取上下文路径-/servlet
				String loginPage = "/login.html";
				StringBuilder builder = new StringBuilder();
				builder.append("<script type=\"text/javascript\">");
				builder.append("window.top.location.href='");
				builder.append(contextPath + loginPage);
				builder.append("';");
				builder.append("</script>");
				out.print(builder.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 登陆超时
	 * @return
	 */
	@RequestMapping(value = "timeout", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> timeout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 判断验证码是否为空
		UserLogin userlogin = null != session.getAttribute(LoginUtils.LOGIN_USERINFO) ? (UserLogin)session.getAttribute(LoginUtils.LOGIN_USERINFO) : null;
		if (null != userlogin) {
			String name = userlogin.getUlUsername();
			return ReturnUtils.successInfo(name);
		}
		try{

		}catch (NullPointerException ex){
			throw new NullPointerException("");
		}
		return ReturnUtils.failInfo("The user is not logged in or logged out!");
	}
	/**
	 * 登陆超时
	 * @return
	 */
	@RequestMapping(value = "changePwd", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> changePwd(String oldPassWord,String newPassWord,HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Map<String, Object> rtnMap = new HashMap<>();
		// 判断验证码是否为空
		UserLogin userlogin = null != session.getAttribute(LoginUtils.LOGIN_USERINFO) ? (UserLogin)session.getAttribute(LoginUtils.LOGIN_USERINFO) : null;
		if (null != userlogin) {
			String name = userlogin.getUlUsername();
			try {
				return userLoginService.changePwd(oldPassWord,newPassWord,name);
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		rtnMap.put("state", 404);
		rtnMap.put("msg", "登录超时请重新登录！");
		return rtnMap;
	}

	@RequestMapping(value = "userMsg", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> userMsg(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Map<String, Object> rtnMap = new HashMap<>();
		// 判断验证码是否为空
		UserLogin userlogin = null != session.getAttribute(LoginUtils.LOGIN_USERINFO) ? (UserLogin)session.getAttribute(LoginUtils.LOGIN_USERINFO) : null;
		if (null != userlogin) {
			String name = userlogin.getUlUsername();
			try {
				UserLogin userlogin1 = userLoginService.getUserMsg(name);
				Map<String, Object> result = new HashMap<>();
				result.put("state", true);
				result.put("data", userlogin1);
				return result;
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		rtnMap.put("state", 404);
		rtnMap.put("msg", "登录超时请重新登录！");
		return rtnMap;
	}
}