package com.platform.service;

import com.platform.domain.Goods;
import com.platform.domain.Menu;
import com.platform.domain.Orders;
import com.platform.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface IUserService {
    User Login(String zh, String pwd);

    Boolean addUser(User user);

    Boolean updateUser(User user);

    User getUserInfo(User user);

    List<Goods> getAllGoods();

    Boolean addOrder(Orders orders);

    Boolean updateOrder(Orders orders);

    List<Orders> getOrderInfo(Orders orders);

    ArrayList<Menu> getMenu(int rid);
}
