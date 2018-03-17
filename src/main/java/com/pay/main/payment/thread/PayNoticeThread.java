package com.pay.main.payment.thread;

import com.pay.main.payment.util.KafkaUtil;

/**
 * 异步通知发送数据Kafka
 * 
 * @author Guo
 */
public class PayNoticeThread implements Runnable {
	String topic;
	String body;

	public PayNoticeThread(String topic, String body) {
		super();
		this.topic = topic;
		this.body = body;
	}

	@Override
	public void run() {
		KafkaUtil.producer(topic, body);
	}
}