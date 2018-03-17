package com.pay.main.payment.util;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pay.main.payment.config.ApplicationConfig;

public class KafkaUtil {
	private static Logger logger = LoggerFactory.getLogger(KafkaUtil.class);

	public static final String PAY_NOTICE_PLACE = "PAY_NOTICE_PLACE";
	public static final String PAY_NOTICE_SUCCESS = "PAY_NOTICE_SUCCESS";

	private static Producer<String, String> producer;
	static {
		producer = KafkaUtil.getProperties();
	}

	/**
	 * 生产者
	 * 
	 * @return
	 */
	private static Producer<String, String> getProperties() {
		Properties props = new Properties();
		props.put("metadata.broker.list", ApplicationConfig.KAFKA_URL);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("key.serializer.class", "kafka.serializer.StringEncoder");
		// 可选配置，如果不配置，则使用默认的partitioner
		props.put("request.required.acks", "1");
		ProducerConfig propertiesConfig = new ProducerConfig(props);
		return new Producer<String, String>(propertiesConfig);
	}

	/**
	 * 生产消息page_visits
	 * 
	 * @return
	 */
	public static boolean producer(String topic, String body) {
		try {
			// 如果topic不存在，则会自动创建，默认replication-factor为1,partitions为0
			KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, body);
			producer.send(data);
			return true;
		} catch (Exception ex) {
			logger.error("发送生产消息错误：", ex);
		}
		return false;
	}

	public static void main(String[] args) {
		Producer<String, String> producer = KafkaUtil.getProperties();
		// 产生并发送消息
		while (true) {
			// 如果topic不存在，则会自动创建，默认replication-factor为1,partitions为0
			KeyedMessage<String, String> data = new KeyedMessage<String, String>("page_visits",
					"测试信息：" + System.currentTimeMillis());
			producer.send(data);
			System.err.println("成功发送信息！");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 关闭producer
		// producer.close();
	}
}