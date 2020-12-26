package com.platform.domain;

import java.sql.Date;

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

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public int getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(int goods_number) {
        this.goods_number = goods_number;
    }

    public double getGoods_weight() {
        return goods_weight;
    }

    public void setGoods_weight(double goods_weight) {
        this.goods_weight = goods_weight;
    }

    public int getGoods_state() {
        return goods_state;
    }

    public void setGoods_state(int goods_state) {
        this.goods_state = goods_state;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public Date getUpd_time() {
        return upd_time;
    }

    public void setUpd_time(Date upd_time) {
        this.upd_time = upd_time;
    }

    public int getVersion_id() {
        return version_id;
    }

    public void setVersion_id(int version_id) {
        this.version_id = version_id;
    }

    public String getGoods_introduce() {
        return goods_introduce;
    }

    public void setGoods_introduce(String goods_introduce) {
        this.goods_introduce = goods_introduce;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }
    //private Attr attrs;              // 商品参数


}
