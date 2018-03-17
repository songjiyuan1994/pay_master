package com.pay.main.payment.vo;

/**
 * 融脉异步消息对象VO
 * 
 * @author Administrator
 *
 */
public class RMNotifyVO {
	private String transactionId; // 微信订单号
	private String cpOrderNo; // 融脉订单号
	private String mchOrderNo; // 商户订单号
	private String resultCode; // 业务处理结果
	private String timeEnd; // 交易结束时间
	private Float totalFee; // 交易总金额
	private String sign; // 签名
	private String bankType;// 文档里没有

	// private String return_code; // 物理通讯结果
	// private String returnMsg;
	// private String errCode;
	// private String errCodeDes;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCpOrderNo() {
		return cpOrderNo;
	}

	public void setCpOrderNo(String cpOrderNo) {
		this.cpOrderNo = cpOrderNo;
	}

	public String getMchOrderNo() {
		return mchOrderNo;
	}

	public void setMchOrderNo(String mchOrderNo) {
		this.mchOrderNo = mchOrderNo;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Float getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Float totalFee) {
		this.totalFee = totalFee;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
}