package com.pay.main.payment.dao;

import org.apache.ibatis.annotations.Param;

import com.pay.main.payment.entity.Dict;

public interface DictMapper {
	Dict selectByPrimaryKey(@Param("type") String type, @Param("key") String key);
}