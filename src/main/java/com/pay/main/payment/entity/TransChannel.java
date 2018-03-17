package com.pay.main.payment.entity;

import java.util.Date;

/**
 * 数据库实体类
 */
public class TransChannel {
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

	private Integer notifyState;

	private String ext4;

	private String ext5;


	public TransChannel() {
		super();
	}

	public TransChannel(String merId, String merDescribe, String tradeNo, String merchantNo, String pChannel, String prince, String pTitle, String pAttach, String pType, Date pTime, int pState, Date createTime, String appId, String notifyUrl) {
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
		this.pType = type;
		this.pTime = pTime;
		this.pState = pState;
		this.createTime = createTime;
		this.appId = appId;
		this.notifyUrl = notifyUrl;
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

	public Integer getNotifyState() {
		return notifyState;
	}

	public void setNotifyState(Integer notifyState) {
		this.notifyState = notifyState;
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
		StringBuilder builder = new StringBuilder();
		builder.append("TransChannel [id=");
		builder.append(id);
		builder.append(", merId=");
		builder.append(merId);
		builder.append(", merDescribe=");
		builder.append(merDescribe);
		builder.append(", tradeNo=");
		builder.append(tradeNo);
		builder.append(", merchantNo=");
		builder.append(merchantNo);
		builder.append(", channelNo=");
		builder.append(channelNo);
		builder.append(", wechatNo=");
		builder.append(wechatNo);
		builder.append(", pChannel=");
		builder.append(pChannel);
		builder.append(", pFee=");
		builder.append(pFee);
		builder.append(", pTitle=");
		builder.append(pTitle);
		builder.append(", pAttach=");
		builder.append(pAttach);
		builder.append(", pType=");
		builder.append(pType);
		builder.append(", pTime=");
		builder.append(pTime);
		builder.append(", pState=");
		builder.append(pState);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", modifiedTime=");
		builder.append(modifiedTime);
		builder.append(", appId=");
		builder.append(appId);
		builder.append(", notifyUrl=");
		builder.append(notifyUrl);
		builder.append(", notify_state=");
		builder.append(notifyState);
		builder.append(", ext4=");
		builder.append(ext4);
		builder.append(", ext5=");
		builder.append(ext5);
		builder.append("]");
		return builder.toString();
	}
}