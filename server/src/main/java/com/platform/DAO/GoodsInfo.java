package com.platform.DAO;

import lombok.Data;

import java.util.List;

@Data
public class GoodsInfo {
    private String name;       //查询参数
    private int pagenum;      //当前页码
    private int pagesize;     //每页商品数量
    private int total;        //商品总数
    private List<Goods> goods;//商品参数


}
