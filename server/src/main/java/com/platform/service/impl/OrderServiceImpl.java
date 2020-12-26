package com.platform.service.impl;

import com.platform.DAO.Orders;
import com.platform.mapper.OrderMapper;
import com.platform.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Boolean addOrder(Orders orders) {
        return orderMapper.addOrder(orders);
    }

    @Override
    public Boolean updateOrder(Orders orders) {
        return orderMapper.updateOrder(orders);
    }

    @Override
    public List<Orders> getOrderInfo(Orders orders) {
        return orderMapper.getOrderInfo(orders);
    }
}
