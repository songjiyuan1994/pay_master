package com.pay.main.payment.dao;

import java.util.Map;

import com.pay.main.payment.entity.UserInfo;

public interface UserInfoMapper {
	public int insertSelective(Map<String, Object> param);

	public UserInfo selectByPrimaryKey(Integer uId);

	public int updateByPrimaryKeySelective(UserInfo record);

	public int updateByPrimaryKey(UserInfo record);
}