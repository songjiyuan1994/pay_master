package com.pay.main.payment.thread;

/**
 * 快速调用线程
 * 
 * @author Guo
 */
public class RunThread {
	public static void sendMessage(String key, String body) {
		// 开启线程-发送支付成功消息到kafka
		PayNoticeThread pn = new PayNoticeThread(key, body);
		Thread thread = new Thread(pn);
		thread.start();
	}
}
