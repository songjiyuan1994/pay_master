package com.pay.main.payment.entity;

import java.util.Date;

/**
 * 数据库实体类
 */
public class PayChannel {
	private Integer id;

	private String merId;

	private String merDescribe;

	private String tradeNo;

	private String merchantNo;

	private String channelNo;

	private String wechatNo;

	private String pChannel;

	private Float pFee;

	private String pTitle;

	private String pAttach;

	private Integer pType;

	private Date pTime;

	private Integer pState;

	private Date createTime;

	private Date modifiedTime;

	private String appId; // 改code

	private String notifyUrl;

	private String ext3;

	private String ext4;

	private String ext5;

	private String notifyState;

	public PayChannel() {
		super();
	}

	public PayChannel(String merId, String merDescribe, String tradeNo, String merchantNo, String pChannel, String prince, String pTitle, String pAttach, int pType, Date pTime, int pState, Date createTime, String appId, String notifyUrl) {
		// 处理数据
		float pFee = Float.parseFloat(prince);
		Integer type = 0;

		if ("WA".equals(pType)) {
			type = 1; // 微信APP
		} else if ("WS".equals(pType)) {
			type = 2; // 微信扫码
		} else if ("WP".equals(pType)) {
			type = 3; // 微信公众号
		} else if ("WW".equals(pType)) {
			type = 4; // 微信WAP
		} else if ("AA".equals(pType)) {
			type = 6; // 支付宝APP
		} else if ("AS".equals(pType)) {
			type = 7; // 支付宝扫码
		} else if ("AW".equals(pType)) {
			type = 8; // 支付宝WAP
		}

		this.merId = merId;
		this.merDescribe = merDescribe;
		this.tradeNo = tradeNo;
		this.merchantNo = merchantNo;
		this.pChannel = pChannel;
		this.pFee = pFee;
		this.pTitle = pTitle;
		this.pAttach = pAttach;
		this.pType = pType;
		this.pTime = pTime;
		this.pState = pState;
		this.createTime = createTime;
		this.appId = appId;
		this.notifyUrl = notifyUrl;
	}
	public String getNotifyState() {
		return notifyState;
	}

	public void setNotifyState(String notifyState) {
		this.notifyState = notifyState;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerDescribe() {
		return merDescribe;
	}

	public void setMerDescribe(String merDescribe) {
		this.merDescribe = merDescribe;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getWechatNo() {
		return wechatNo;
	}

	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}

	public String getpChannel() {
		return pChannel;
	}

	public void setpChannel(String pChannel) {
		this.pChannel = pChannel;
	}

	public Float getpFee() {
		return pFee;
	}

	public void setpFee(Float pFee) {
		this.pFee = pFee;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public String getpAttach() {
		return pAttach;
	}

	public void setpAttach(String pAttach) {
		this.pAttach = pAttach;
	}

	public Integer getpType() {
		return pType;
	}

	public void setpType(Integer pType) {
		this.pType = pType;
	}

	public Date getpTime() {
		return pTime;
	}

	public void setpTime(Date pTime) {
		this.pTime = pTime;
	}

	public Integer getpState() {
		return pState;
	}

	public void setpState(Integer pState) {
		this.pState = pState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	public String getExt5() {
		return ext5;
	}

	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}

	@Override
	public String toString() {
		return "PayChannel [id=" + id + ", merId=" + merId + ", merDescribe=" + merDescribe + ", tradeNo=" + tradeNo + ", merchantNo=" + merchantNo + ", channelNo=" + channelNo + ", wechatNo=" + wechatNo + ", pChannel=" + pChannel + ", pFee=" + pFee + ", pTitle=" + pTitle + ", pAttach=" + pAttach + ", pType=" + pType + ", pTime=" + pTime + ", pState=" + pState + ", createTime=" + createTime + ", modifiedTime=" + modifiedTime + ", appId=" + appId + ", notifyUrl=" + notifyUrl + ", ext3=" + ext3 + ", ext4=" + ext4 + ", ext5=" + ext5 + "]";
	}
}