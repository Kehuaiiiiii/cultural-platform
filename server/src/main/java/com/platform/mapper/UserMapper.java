package com.platform.mapper;

import com.platform.domain.Goods;
import com.platform.domain.Menu;
import com.platform.domain.Orders;
import com.platform.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;


public interface UserMapper{

    User login(@Param("zh") String zh, @Param("pwd") String pwd);

    Boolean addUser(User user);

    Boolean updateUser(User user);

    User getUserInfo(User user);

    List<Goods> getAllGoods();

    Boolean addOrder(Orders orders);

    Boolean updateOrder(Orders orders);

    List<Orders> getOrderInfo(Orders orders);


}
