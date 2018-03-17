package com.pay.main.payment.dao;

import com.pay.main.payment.entity.PaySummaryEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaySummaryMapper {

    List<PaySummaryEntity> getSummaryTotal(@Param("merId") String merId
            , @Param("startTime") String startTime
            , @Param("endTime") String endTime);
}