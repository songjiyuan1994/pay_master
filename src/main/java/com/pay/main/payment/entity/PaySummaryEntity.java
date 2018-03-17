package com.pay.main.payment.entity;

/**
 * Created by songjiyuan on 17/7/9.
 */
public class PaySummaryEntity {

    private String merId;

    private int successCount;

    private String date;

    private float totalMoney;

    private float settlement;

    private String payType;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public float getSettlement() {
        return settlement;
    }

    public void setSettlement(float settlement) {
        this.settlement = settlement;
    }

}
