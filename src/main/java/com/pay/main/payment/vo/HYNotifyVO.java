package com.pay.main.payment.vo;

public class HYNotifyVO {
	private String result;
	private String pay_message;
	private String agent_id;
	private String jnet_bill_no;
	private String agent_bill_id;
	private String pay_type;
	private String pay_amt;
	private String remark;
	private String sign;
	private String fbtn;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPay_message() {
		return pay_message;
	}

	public void setPay_message(String pay_message) {
		this.pay_message = pay_message;
	}

	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}

	public String getJnet_bill_no() {
		return jnet_bill_no;
	}

	public void setJnet_bill_no(String jnet_bill_no) {
		this.jnet_bill_no = jnet_bill_no;
	}

	public String getAgent_bill_id() {
		return agent_bill_id;
	}

	public void setAgent_bill_id(String agent_bill_id) {
		this.agent_bill_id = agent_bill_id;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getPay_amt() {
		return pay_amt;
	}

	public void setPay_amt(String pay_amt) {
		this.pay_amt = pay_amt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getFbtn() {
		return fbtn;
	}

	public void setFbtn(String fbtn) {
		this.fbtn = fbtn;
	}

	public String toString() {
		String wholeStr = "result=" + result + "&pay_message=" + pay_message
				+ "&agent_id=" + agent_id + "&pay_type=" + pay_type
				+ "&jnet_bill_no=" + jnet_bill_no + "&agent_bill_id="
				+ agent_bill_id + "&pay_amt=" + pay_amt + "&remark=" + remark
				+ "&sign=" + sign + "&fbtn=" + fbtn;
		return wholeStr;
	}
}