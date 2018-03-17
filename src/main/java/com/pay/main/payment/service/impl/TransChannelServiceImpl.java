package com.pay.main.payment.service.impl;

import com.pay.main.payment.dao.TransChannelMapper;
import com.pay.main.payment.dao.TransChannelTotalMapper;
import com.pay.main.payment.entity.PayChannel;
import com.pay.main.payment.entity.TransChannel;
import com.pay.main.payment.service.ITransChannelService;
import com.pay.main.payment.util.DataProcessUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("transChannelService")
public class TransChannelServiceImpl implements ITransChannelService {
	Logger logger = LoggerFactory.getLogger(TransChannelServiceImpl.class);

	@Autowired
	TransChannelMapper transChannelMapper;
	@Autowired
	TransChannelTotalMapper transChannelTotalMapper;

	@Override
	@Transactional
	public boolean insertSelective(PayChannel record) throws Exception {
		Map<String, Object> param = DataProcessUtil.convertBeanToMap(record);
		int index1 = transChannelMapper.insertSelective(param);
		int index2 = transChannelTotalMapper.insertSelective(param);
		boolean bool = index1 > 0 && index2 > 0;
		return bool;
	}

	@Override
	@Transactional
	public boolean updateByPrimaryKeySelective(PayChannel record) throws Exception {
		Map<String, Object> param = DataProcessUtil.convertBeanToMap(record);
		int index1 = transChannelMapper.updateByPrimaryKeySelective(param);
		boolean bool = index1 > 0;
		return bool;
	}

	@Override
	public PayChannel getByPrimaryKey(String tradeNo) {
		PayChannel payChannel = null;
		try {
			payChannel = transChannelMapper.selectByPrimaryKey(tradeNo);
		} catch (Exception ex) {
			logger.error("PayChannelServiceImpl-selectByPrimaryKey is error:", ex);
		}
		return payChannel;
	}

	@Override
	public PayChannel getByPrimaryKeyMer(String merId, String merNo) {
		PayChannel payChannel = null;
		try {
			payChannel = transChannelMapper.selectByPrimaryKeyMer(merId, merNo);
		} catch (Exception ex) {
			logger.error("PayChannelServiceImpl-getByPrimaryKeyMer is error:", ex);
		}
		return payChannel;
	}
}