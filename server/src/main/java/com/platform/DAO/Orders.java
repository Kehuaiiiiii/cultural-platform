package com.platform.DAO;

import lombok.Data;

import java.sql.Date;

@Data
public class Orders {

    private int id;             //订单id
    private int buyer_id;       //用户id
    private int seller_id;      //商家id
    private int goods_id;       //商品id
    private int goods_number;   //购买商品数量
    private double order_price; //订单支付价格
    private int pay_status;     //支付状态 0：未支付，1：已支付
    private int send_status;    //发货状态 0：未发货，1：已发货，2：已收货，3：已退回
    private String created_time;  //订单创建时间
    private String modified_time; //更新时间
    private int order_status;   //订单状态 0：已取消，1：进行中，2：已完成
    private String address;     //收货地址

}
