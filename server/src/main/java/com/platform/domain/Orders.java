package com.platform.domain;

public class Orders {

    private int id;             //订单id
    private int user_id;        //用户id
    private int merchant_id;    //商家id
    private int pay_status;     //支付状态 0：未支付，1：已支付
    private int send_status;    //发货状态 0：未发货，1：已发货，2：已收货，3：已退回
    private double order_price; //订单支付价格
    private int order_number;   //订单数量
    private int order_status;   //订单状态 0：已取消，1：进行中，2：已完成

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    public int getSend_status() {
        return send_status;
    }

    public void setSend_status(int send_status) {
        this.send_status = send_status;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }


}
