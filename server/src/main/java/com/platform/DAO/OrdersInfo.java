package com.platform.domain;

import java.util.List;

public class OrdersInfo {
    private Orders query;       //查询参数
    private int pagenum;        //当前页码
    private int pagesize;       //每页订单数量

    public Orders getQuery() {
        return query;
    }

    public void setQuery(Orders query) {
        this.query = query;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    private int total;          //订单总数
    private List<Orders> orders;//订单参数
}
