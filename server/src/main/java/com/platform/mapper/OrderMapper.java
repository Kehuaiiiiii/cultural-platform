package com.platform.mapper;

import com.platform.DAO.Orders;
import com.platform.DAO.OrdersInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    Integer addOrder(Orders orders);

    Integer updOrderSend(Orders orders);

    Integer updOrderPay(Orders orders);

    List<Orders> getOrders(@Param("uid") int uid, @Param("min")int min, @Param("max")int max);

    int getTotal(int uid);

    int getAllTotal();

    List<Orders> getAllOrders(@Param("min")int min,@Param("max")int max);

    List<Orders> getOrdAdmin(@Param("list") List<Integer> list , @Param("min") int min , @Param("max") int max);

    int getTotalAdmin(@Param("list")List<Integer> list);

    Integer deleteOrder(int id);
}
