package com.pay.main.payment.vo;

/**
 * 下单传参实体类
 */
public class PayOrderVO {
	String payType; // 支付方式 APP,SCAN
	String merId; // 商户号
	String merNo; // 订单号
	String code; // 编号
	String title; // 商品名称
	String describe;// 订单描述
	String attach; // 自定义参数
	String price;
	String synUrl; // 支付后跳转地址
	String notifyUrl; // 商户通知地址
	String ip; // 用户地址

	String orderNo;
	String openId;
	String callbackUrl;

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String prince) {
		this.price = prince;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerNo() {
		return merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getSynUrl() {
		return synUrl;
	}

	public void setSynUrl(String synUrl) {
		this.synUrl = synUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "PayOrderVO [payType=" + payType + ", merId=" + merId
				+ ", merNo=" + merNo + ", code=" + code + ", title=" + title
				+ ", describe=" + describe + ", attach=" + attach + ", price="
				+ price + ", synUrl=" + synUrl + ", notifyUrl=" + notifyUrl
				+ ", ip=" + ip + ", orderNo=" + orderNo + ", openId=" + openId
				+ ", callbackUrl=" + callbackUrl + "]";
	}
}