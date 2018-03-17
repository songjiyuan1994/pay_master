package com.pay.main.payment.vo;

/**
 * 通知商户信息接口
 * 
 * @author Guo
 */
public class RMSendNotifyVO {
	public RMSendNotifyVO(String mch_id, String out_trade_no,
						  String attach, String body, String state,
						  String total_fee, String platform_no,
						  String pay_no,String pay_type ,String sign) {
		this.mch_id = mch_id;
		this.out_trade_no = out_trade_no;
		this.attach = attach;
		this.body = body;
		this.state = state;
		this.total_fee = total_fee;
		this.platform_no = platform_no;
		this.pay_no = pay_no;
		this.pay_type = pay_type;
		this.sign = sign;
	}

	private String mch_id; // 商户订单号
	private String out_trade_no; // 商户订单号
	private String attach; // 用户参数
	private String body; // 订单描述
	private String state; // 支付状态
	private String total_fee; // 支付类型
	private String platform_no; // 平台订单号
	private String pay_no; // 支付订单号
	private String sign; // 签名
	private String pay_type; // 支付方式

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getPlatform_no() {
		return platform_no;
	}

	public void setPlatform_no(String platform_no) {
		this.platform_no = platform_no;
	}

	public String getPay_no() {
		return pay_no;
	}

	public void setPay_no(String pay_no) {
		this.pay_no = pay_no;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "RMSendNotifyVO{" +
				"mch_id='" + mch_id + '\'' +
				", out_trade_no='" + out_trade_no + '\'' +
				", attach='" + attach + '\'' +
				", body='" + body + '\'' +
				", state='" + state + '\'' +
				", total_fee='" + total_fee + '\'' +
				", platform_no='" + platform_no + '\'' +
				", pay_no='" + pay_no + '\'' +
				", sign='" + sign + '\'' +
				'}';
	}
}