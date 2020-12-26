package com.platform.service;

import com.platform.domain.Orders;
import com.platform.domain.OrdersInfo;


public interface IOrderService {
    Boolean addOrder(Orders orders);

    Boolean updateOrder(Orders orders);

    OrdersInfo getOrderInfo(int uid,OrdersInfo ordersInfo);
}
