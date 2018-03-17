package com.pay.main.payment.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.main.payment.dao.UserInfoMapper;
import com.pay.main.payment.entity.UserInfo;
import com.pay.main.payment.service.IUserInfoService;
import com.pay.main.payment.util.DataProcessUtil;

@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
	@Autowired
	UserInfoMapper userInfoMapper;

	@Override
	public int insertSelective(UserInfo record) {
		Map<String, Object> param = DataProcessUtil.convertBeanToMap(record);
		int insert = userInfoMapper.insertSelective(param);
		return insert;
	}

	@Override
	public UserInfo selectByPrimaryKey(Integer uId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(UserInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(UserInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}
}