package com.platform.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderInfo {
    private int id;
    private String buyer_name;
    private String seller_name;
    private String goods_name;
    private int goods_number;
    private double goods_price;
    private double order_price;
    private int pay_status;
    private int send_status;
    private String created_time;
    private String modified_time;
    private String address;
}
