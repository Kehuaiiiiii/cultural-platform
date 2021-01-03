package com.platform.service;


import com.platform.DAO.Orders;
import com.platform.DAO.OrdersInfo;
import com.platform.VO.GetOrderInfoRequest;
import com.platform.VO.GetOrderInfoResponse;

public interface IOrderService {
    Boolean addOrder(Orders orders);

    Boolean updOrderSend(Orders orders);

    Boolean updOrderPay(Orders orders);

    GetOrderInfoResponse getOrderInfo(int uid, GetOrderInfoRequest request);

    OrdersInfo searchOrder(String username,OrdersInfo ordersInfo);

    boolean deleteOrder(int id);
}
