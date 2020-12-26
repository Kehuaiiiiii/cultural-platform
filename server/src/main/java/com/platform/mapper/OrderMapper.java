package com.platform.mapper;

import com.platform.DAO.Orders;

import java.util.List;

public interface OrderMapper {
    Boolean addOrder(Orders orders);

    Boolean updateOrder(Orders orders);

    List<Orders> getOrderInfo(Orders orders);
}
