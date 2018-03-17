package com.pay.main.payment.service;

import com.pay.main.payment.entity.PaySummaryEntity;

import java.util.List;

/**
 * Created by songjiyuan on 17/7/9.
 */
public interface IPaySummaryService {

    List<PaySummaryEntity> getSummaryTotal(String ulMerId, String startDate, String endDate);

}
