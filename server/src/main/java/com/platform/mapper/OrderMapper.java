package com.platform.mapper;

import com.platform.DAO.Orders;
import com.platform.DAO.OrdersInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    Integer addOrder(Orders orders);

    Integer updOrderSend(Orders orders);

    Integer updOrderPay(Orders orders);

    List<Orders> getOrders(@Param("uid") int uid, @Param("payStatus") Integer payStatus,
                           @Param("sendStatus") Integer sendStatus, @Param("min") int min, @Param("max") int max);

    int getTotal(@Param("uid") int uid, @Param("payStatus") Integer payStatus, @Param("sendStatus") Integer sendStatus);

    int getAllTotal(@Param("uid") Integer uid, @Param("payStatus") Integer payStatus, @Param("sendStatus") Integer sendStatus);

    List<Orders> getAllOrders(@Param("uid") Integer uid, @Param("payStatus") Integer payStatus,
                              @Param("sendStatus") Integer sendStatus, @Param("min") int min, @Param("max") int max);

    List<Orders> getOrdAdmin(@Param("list") List<Integer> list, @Param("min") int min, @Param("max") int max);

    int getTotalAdmin(@Param("list") List<Integer> list);

    Integer deleteOrder(int id);
}
