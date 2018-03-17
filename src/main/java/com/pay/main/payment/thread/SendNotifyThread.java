//package com.pay.main.payment.thread;
//
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.pay.main.payment.entity.PayChannel;
//import com.pay.main.payment.entity.UserLogin;
//import com.pay.main.payment.service.IUserLoginService;
//import com.pay.main.payment.util.DataProcessUtil;
//import com.pay.main.payment.util.EncryptionUtil;
//import com.pay.main.payment.util.HttpUtil;
//import com.pay.main.payment.vo.RMSendNotifyVO;
//
///**
// * 异步线程通知
// *
// * @author Guo
// */
//public class SendNotifyThread implements Runnable {
//	private static Logger logger = LoggerFactory.getLogger(SendNotifyThread.class);
//
//	@Autowired
//	IUserLoginService userLoginService;
//
//	private PayChannel payChannel;
//	private String state;
//
//	public SendNotifyThread(PayChannel payChannel, String state) {
//		super();
//		this.payChannel = payChannel;
//		this.state = state;
//	}
//
//	@Override
//	public void run() {
//		// 如果payChannel等于null 跳出程序
//		if (null == payChannel) {
//			return;
//		}
//		try {
//			boolean bool = false;
//			Integer index = 0;
//			while (index < 5) {
//				String merNo = payChannel.getMerchantNo();
//				String title = payChannel.getpTitle();
//				String attach = payChannel.getpAttach();
//				String prince = payChannel.getpFee() + "";
//				String type = payChannel.getpType() + "";
//				String appId = payChannel.getAppId();
//				String notifyUrl = payChannel.getNotifyUrl();
//
//				String merId = payChannel.getMerId();
//				UserLogin userInfo = userLoginService.getByPrimaryKey(merId);
//
//				RMSendNotifyVO vo = new RMSendNotifyVO(merNo, title, attach, prince, state, type, appId, null);
//				Map<String, Object> params = DataProcessUtil.convertBeanNotNullToMap(vo);
//				params.remove("sign"); // 删除sign
//				logger.info("回调信息需要加密数据：" + params);
//				String textLink = DataProcessUtil.textLink(params, true); // 拼接字符串
//				String sign = EncryptionUtil.md5s(textLink + "&&&" + userInfo.getUlSign());
//				params.put("sign", sign);
//
//				String doPost = HttpUtil.doPost(notifyUrl, params, "UTF-8");
//				logger.info("（" + merNo + "）通知商户返回信息：" + doPost);
//				String temp = (doPost + "").toUpperCase().trim();
//				if (!"SUCCESS".equals(temp)) {
//					bool = true;
//				} else {
//					Thread.sleep(5000);
//				}
//				index++;
//				index = bool ? 99 : index;
//			}
//		} catch (Exception ex) {
//			logger.error("通知商户发送异步数据-sendNotify：", ex);
//		}
//	}
//}