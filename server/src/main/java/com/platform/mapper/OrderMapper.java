package com.platform.mapper;

import com.platform.DAO.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    Boolean addOrder(Orders orders);

    Boolean updateOrder(Orders orders);

    List<Orders> getOrders(@Param("uid") int uid, @Param("min")int min, @Param("max")int max);

    int getTotal(int uid);

    int getAllTotal();

    List<Orders> getAllOrders(@Param("min")int min,@Param("max")int max);
}
