package com.pay.main.payment.entity;

/**
 * 用户信息查询（获取用户签名）
 * 
 * @author Guo
 */
public class UserLogin {
	private Integer id;
	private String ulUsername;
	private String ulPassword;
	private String ulMerId;
	private String ulSign;
	private Integer ulGroup;
	private Integer ulPaystate;
	private String ulNotify;
	private String name;
	private String phone;
	private String pdType;
	private String shoukuanren;
	private String kaihuhang;
	private String banktype;
	private String zhanghu;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUlUsername() {
		return ulUsername;
	}

	public void setUlUsername(String ulUsername) {
		this.ulUsername = ulUsername;
	}

	public String getUlPassword() {
		return ulPassword;
	}

	public void setUlPassword(String ulPassword) {
		this.ulPassword = ulPassword;
	}

	public String getUlMerId() {
		return ulMerId;
	}

	public void setUlMerId(String ulMerId) {
		this.ulMerId = ulMerId;
	}

	public String getUlSign() {
		return ulSign;
	}

	public void setUlSign(String ulSign) {
		this.ulSign = ulSign;
	}

	public Integer getUlGroup() {
		return ulGroup;
	}

	public void setUlGroup(Integer ulGroup) {
		this.ulGroup = ulGroup;
	}

	public Integer getUlPaystate() {
		return ulPaystate;
	}

	public void setUlPaystate(Integer ulPaystate) {
		this.ulPaystate = ulPaystate;
	}

	public String getUlNotify() {
		return ulNotify;
	}

	public void setUlNotify(String ulNotify) {
		this.ulNotify = ulNotify;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPdType() {
		return pdType;
	}

	public void setPdType(String pdType) {
		this.pdType = pdType;
	}

	public String getShoukuanren() {
		return shoukuanren;
	}

	public void setShoukuanren(String shoukuanren) {
		this.shoukuanren = shoukuanren;
	}

	public String getKaihuhang() {
		return kaihuhang;
	}

	public void setKaihuhang(String kaihuhang) {
		this.kaihuhang = kaihuhang;
	}

	public String getBanktype() {
		return banktype;
	}

	public void setBanktype(String banktype) {
		this.banktype = banktype;
	}

	public String getZhanghu() {
		return zhanghu;
	}

	public void setZhanghu(String zhanghu) {
		this.zhanghu = zhanghu;
	}


}