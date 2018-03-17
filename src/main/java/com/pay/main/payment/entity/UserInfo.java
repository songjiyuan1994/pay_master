package com.pay.main.payment.entity;

import java.util.Date;

/**
 * 数据库实体类
 */
public class UserInfo {
	private Integer uId;

	private String uMerid;

	private String uImsi;

	private String uImei;

	private String uModel;

	private String uPhone;

	private String uArea;

	private String uMac;

	private String uIp;

	private Integer uState;

	private Date createTime;

	private Date modifiedTime;

	private String uAppid;

	private String ext2;

	private String ext3;

	private String ext4;

	private String ext5;

	public UserInfo(String uMerid, String uImsi, String uImei, String uModel, String uPhone, String uArea, String uMac, String uIp, Integer uState, Date createTime) {
		super();
		this.uMerid = uMerid;
		this.uImsi = uImsi;
		this.uImei = uImei;
		this.uModel = uModel;
		this.uPhone = uPhone;
		this.uArea = uArea;
		this.uMac = uMac;
		this.uIp = uIp;
		this.uState = uState;
		this.createTime = createTime;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getuAppid() {
		return uAppid;
	}

	public void setuAppid(String uAppid) {
		this.uAppid = uAppid;
	}

	public String getuImsi() {
		return uImsi;
	}

	public void setuImsi(String uImsi) {
		this.uImsi = uImsi;
	}

	public String getuImei() {
		return uImei;
	}

	public void setuImei(String uImei) {
		this.uImei = uImei;
	}

	public String getuModel() {
		return uModel;
	}

	public void setuModel(String uModel) {
		this.uModel = uModel;
	}

	public String getuPhone() {
		return uPhone;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}

	public String getuArea() {
		return uArea;
	}

	public void setuArea(String uArea) {
		this.uArea = uArea;
	}

	public String getuMac() {
		return uMac;
	}

	public void setuMac(String uMac) {
		this.uMac = uMac;
	}

	public String getuIp() {
		return uIp;
	}

	public void setuIp(String uIp) {
		this.uIp = uIp;
	}

	public Integer getuState() {
		return uState;
	}

	public void setuState(Integer uState) {
		this.uState = uState;
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

	public String getuMerid() {
		return uMerid;
	}

	public void setuMerid(String uMerid) {
		this.uMerid = uMerid;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
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
}