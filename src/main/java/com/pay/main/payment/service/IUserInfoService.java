package com.pay.main.payment.service;

import com.pay.main.payment.entity.UserInfo;

public interface IUserInfoService {
	public int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(Integer uId);

	int updateByPrimaryKeySelective(UserInfo record);

	int updateByPrimaryKey(UserInfo record);
}