package com.pay.main.payment.service.impl;

import com.pay.main.payment.dao.UserLoginMapper;
import com.pay.main.payment.entity.UserLogin;
import com.pay.main.payment.service.IUserLoginService;
import com.pay.main.payment.util.LoginUtils;
import com.pay.main.payment.util.ReturnUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service("userLoginService")
public class UserLoginServiceImpl implements IUserLoginService {
    private static Log logger = LogFactory.getLog(UserLoginServiceImpl.class);

    @Autowired
    UserLoginMapper userLoginMapper;

    @Override
    public Map<String, Object> getLoginAccount(HttpServletRequest request, String username, String password, String captcha) {
        HttpSession session = request.getSession();
        try {
            // 判断验证码是否为空
            String onCaptcha = null != session.getAttribute(LoginUtils.LOGIN_CAPTCHA) ? session.getAttribute(LoginUtils.LOGIN_CAPTCHA).toString() : null;
            if (null != onCaptcha && onCaptcha.equals(captcha.toUpperCase())) {
                // 用户名密码不可为空
                if (null != username && !"".equals(username) && null != password && !"".equals(password)) {
                    UserLogin userlogin = userLoginMapper.selectByUserPwd(username, password);
                    if (userlogin != null) {
                        session.setAttribute(LoginUtils.LOGIN_USERINFO, userlogin);
                        return ReturnUtils.successInfo("登陆成功！");
                    }
                }
                return ReturnUtils.failInfo("账号密码错误！");
            }
        } catch (Exception ex) {
            logger.error("", ex);
        }
        return ReturnUtils.failInfo("验证码错误！");
    }

    @Override
    public boolean getOutLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute(LoginUtils.LOGIN_USERINFO);
            request.setCharacterEncoding("UTF-8");
            return true;
        } catch (Exception ex) {
            logger.error("退出账号：" + ex);
            return false;
        }
    }

    @Override
    public Map<String, Object> changePwd(String oldPassWord, String newPassWord, String name) {
        Map<String, Object> rtnMap = new HashMap<>();
        int changeLine = userLoginMapper.updatePwd(name, oldPassWord, newPassWord);
        if (changeLine > 0) {
            rtnMap.put("state", 200);
            rtnMap.put("msg", "修改成功！");
        } else {
            rtnMap.put("state", 400);
            rtnMap.put("msg", "旧密码输入错误！");
        }
        return rtnMap;
    }

    @Override
    public UserLogin getUserMsg(String name) {
        UserLogin selectByPrimaryKey = null;
        try {
            selectByPrimaryKey = userLoginMapper.selectByPrimaryKey(name);
        } catch (Exception ex) {
            logger.error("查询用户状态失败：", ex);
        }
        return selectByPrimaryKey;
    }


    @Override
    public UserLogin getByPrimaryKey(String ulMerId) {
        UserLogin selectByPrimaryKey = null;
        try {
            selectByPrimaryKey = userLoginMapper.selectByPrimaryKey(ulMerId);
        } catch (Exception ex) {
            logger.error("查询用户状态失败：", ex);
        }
        return selectByPrimaryKey;
    }
}