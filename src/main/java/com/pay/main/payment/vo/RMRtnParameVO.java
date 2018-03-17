package com.pay.main.payment.vo;

/**
 * 请求支付订单，返回给客户端VO
 * 
 * @author Administrator
 *
 */
public class RMRtnParameVO {
	private String status;

	// APPID
	private String tokenId;

	// SCAN
	private String codeUrl;

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
}