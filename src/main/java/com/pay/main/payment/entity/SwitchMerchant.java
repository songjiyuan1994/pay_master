package com.pay.main.payment.entity;

import java.util.Date;

public class SwitchMerchant {
	private Integer id;
	private String appid;
	private String mchid;
	private String sign;
	private String opeAppid;
	private String pubAppid;
	private String pubSecret;
	private String pubUrl;
	private Float total;
	private Integer state;
	private Date lastPayTime;
	private Date largePayTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOpeAppid() {
		return opeAppid;
	}

	public void setOpeAppid(String opeAppid) {
		this.opeAppid = opeAppid;
	}

	public String getPubAppid() {
		return pubAppid;
	}

	public void setPubAppid(String pubAppid) {
		this.pubAppid = pubAppid;
	}

	public String getPubSecret() {
		return pubSecret;
	}

	public void setPubSecret(String pubSecret) {
		this.pubSecret = pubSecret;
	}

	public String getPubUrl() {
		return pubUrl;
	}

	public void setPubUrl(String pubUrl) {
		this.pubUrl = pubUrl;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getLastPayTime() {
		return lastPayTime;
	}

	public void setLastPayTime(Date lastPayTime) {
		this.lastPayTime = lastPayTime;
	}

	public Date getLargePayTime() {
		return largePayTime;
	}

	public void setLargePayTime(Date largePayTime) {
		this.largePayTime = largePayTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SwitchMerchant [id=");
		builder.append(id);
		builder.append(", appid=");
		builder.append(appid);
		builder.append(", mchid=");
		builder.append(mchid);
		builder.append(", sign=");
		builder.append(sign);
		builder.append(", opeAppid=");
		builder.append(opeAppid);
		builder.append(", pubAppid=");
		builder.append(pubAppid);
		builder.append(", pubSecret=");
		builder.append(pubSecret);
		builder.append(", pubUrl=");
		builder.append(pubUrl);
		builder.append(", total=");
		builder.append(total);
		builder.append(", state=");
		builder.append(state);
		builder.append(", lastPayTime=");
		builder.append(lastPayTime);
		builder.append(", largePayTime=");
		builder.append(largePayTime);
		builder.append("]");
		return builder.toString();
	}
}