package com.platform.DAO;

import lombok.Data;

import java.util.List;

@Data
public class OrdersInfo {
    private String username;       //按用户名查询
    private int pagenum;        //当前页码
    private int pagesize;       //每页订单数量
    private int total;          //订单总数
    private List<Orders> orders;//订单参数
}
