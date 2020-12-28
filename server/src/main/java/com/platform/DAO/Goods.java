package com.platform.DAO;

import lombok.Data;

import java.sql.Date;

@Data
public class Goods {
    private int id;           //商品id
    private int uid;          //商家id
    private String name;      //商品名称
    private double price;     //商品价格
    private int number;       //商品储量
    private double weight;    //商品重量
    private int state;        //状态：0：未通过，1：审核中，2：审核通过
    private Date created_time;       //添加时间
    private String introduce; //商品介绍
    private String url;            //保存图片地址


    //private Attr attrs;              // 商品参数


}
