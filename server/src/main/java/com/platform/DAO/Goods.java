package com.platform.DAO;

import lombok.Data;

import java.sql.Date;

@Data
public class Goods {
    private int goods_id;           //商品id
    private String goods_name;      //商品名称
    private double goods_price;     //商品价格
    private int goods_number;       //商品储量
    private double goods_weight;    //商品重量
    private int goods_state;        //状态：0：未通过，1：审核中，2：审核通过
    private Date add_time;          //添加时间
    private Date upd_time;          //更新时间
    private int version_id;       //更新版本
    private String goods_introduce; //商品介绍
    private String pics;            //保存图片地址


    //private Attr attrs;              // 商品参数


}
