package com.platform.service;

import com.platform.DAO.Orders;

import java.util.List;

public interface IOrderService {
    Boolean addOrder(Orders orders);

    Boolean updateOrder(Orders orders);

    List<Orders> getOrderInfo(Orders orders);
}
