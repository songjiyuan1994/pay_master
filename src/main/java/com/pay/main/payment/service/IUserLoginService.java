package com.pay.main.payment.service;

import com.pay.main.payment.entity.UserLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IUserLoginService {
	public UserLogin getByPrimaryKey(String ulMerId);

	public Map<String, Object> getLoginAccount(HttpServletRequest request, String username, String password, String captcha);
	
	public boolean getOutLogin(HttpServletRequest request, HttpServletResponse response);

	Map<String,Object> changePwd(String oldPassWord, String newPassWord, String name);


	UserLogin getUserMsg(String name);

}