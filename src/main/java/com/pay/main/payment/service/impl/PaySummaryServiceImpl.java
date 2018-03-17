package com.pay.main.payment.service.impl;

import com.pay.main.payment.dao.PaySummaryMapper;
import com.pay.main.payment.entity.PaySummaryEntity;
import com.pay.main.payment.service.IPaySummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by songjiyuan on 17/7/9.
 */
@Service("paySummaryService")
public class PaySummaryServiceImpl implements IPaySummaryService {
    @Autowired
    PaySummaryMapper paySummaryMapper;

    @Override
    public List<PaySummaryEntity> getSummaryTotal(String ulMerId, String startDate, String endDate) {
        return paySummaryMapper.getSummaryTotal(ulMerId, startDate, endDate);
    }

}
