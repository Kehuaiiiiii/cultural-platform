package com.platform.service;

import com.platform.DAO.Orders;
import com.platform.DAO.OrdersInfo;


public interface IOrderService {
    Boolean addOrder(Orders orders);

    Boolean updateOrder(Orders orders);

    OrdersInfo getOrderInfo(int uid,OrdersInfo ordersInfo);
}
