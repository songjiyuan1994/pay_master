package com.pay.main.payment.vo;

/**
 * 退款接口信息-融脉
 * 
 * @author Administrator
 *
 */
public class RMRefundVO {
	private String sign;
	private String status;
	private String cpOrderNo;
	private String resultCode;
	private String cpRefundNo;
	private String refundAmt;
	private String mchOrderNo;
	private String mchRefundNo;
	private String mchNo;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCpOrderNo() {
		return cpOrderNo;
	}

	public void setCpOrderNo(String cpOrderNo) {
		this.cpOrderNo = cpOrderNo;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getCpRefundNo() {
		return cpRefundNo;
	}

	public void setCpRefundNo(String cpRefundNo) {
		this.cpRefundNo = cpRefundNo;
	}

	public String getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(String refundAmt) {
		this.refundAmt = refundAmt;
	}

	public String getMchOrderNo() {
		return mchOrderNo;
	}

	public void setMchOrderNo(String mchOrderNo) {
		this.mchOrderNo = mchOrderNo;
	}

	public String getMchRefundNo() {
		return mchRefundNo;
	}

	public void setMchRefundNo(String mchRefundNo) {
		this.mchRefundNo = mchRefundNo;
	}

	public String getMchNo() {
		return mchNo;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}
}